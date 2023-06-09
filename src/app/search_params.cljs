(ns app.search-params
  (:require
    [uix.core :as uix :refer [defui $]]
    [app.results :as results :refer [results]]
    [app.use-breed-list :as use-breed-list :refer [use-breed-list]]
    [app.fetch-search :as fetch-search :refer [fetch-search]]
    ["@tanstack/react-query" :refer [useQuery]]
        [uix.dom]))

(def animals ["bird", "cat", "dog", "rabbit", "reptile"])


(defn request-pets [set-pets! animal location breed]
  (let [result (js/fetch (str "http://pets-v2.dev-apis.com/pets?animal=" animal "&location=" location "&breed=" breed))]
    (-> result
        (.then (fn [response]
                 (.json response)))
        (.then (fn [json-data]
                 ;;(js/console.log json-data)
                 (set-pets! (:pets (js->clj json-data :keywordize-keys true))))))))


(defui search-parameters [_]
  (let [ [request-params set-request-params!] (uix/use-state (clj->js {:location ""
                                                                       :animal   ""
                                                                       :breed    ""}))

        [animal set-animal!] (uix/use-state "")
        breeds               (first (use-breed-list animal))
        result               (useQuery (clj->js ["search" request-params]) fetch-search)
        is-loading           (.-isLoading result)]


    ($ :div {:className "search-params"}
       ($ :form
          {:onSubmit (fn [e]
                       (.preventDefault e)
                       ;; (js/console.log "this is data " (js/FormData. (.-target e) ))
                       (let [form-data (js/FormData. (.-target e))
                             obj       {:animal   (or (.get form-data "animal") "")
                                        :location (or (.get form-data "location") "")
                                        :breed    (or (.get form-data "breed") "")}]
                         (set-request-params! obj)))}

          ($ :label {:htmlFor "location"}
            "Location"
            ($ :input {:id          "location"
                       :name        "location"
                       :placeholder "Location"}))

          ($ :label {:htmlFor "animal"}
            "Animal"
            ($ :select
               {:id          "animal"
                :value       animal
                :placeholder "Animal"
                :onChange    #(do
                                (set-animal! (.-value (.-target %))))}
               (concat [($ :option {:key "no option"})]
                       (map (fn [animal] ($ :option {:key animal :value animal} animal)) animals))))

          ($ :label {:htmlFor "breed"}
            "Breed"
            ($ :select
               {:id          "breed"
                :disabled    (empty? breeds);;(= (count breeds) 0)
                :name        "breed"
                :placeholder "Breed"}
               (map (fn [breed] ($ :option {:key breed :value breed} breed)) breeds)))

          ($ :button "Submit"))
       (when (not is-loading)#_(and result (.-data result))
             (let [pets (js->clj (.-pets (.-data result)) :keywordize-keys true)]
       ;;(println "this is pets in params" pets)
               ($ results {:pets pets}))

       ))))
