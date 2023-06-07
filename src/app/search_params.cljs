(ns app.search-params
  (:require
    [uix.core :as uix :refer [defui $]]
    [app.pet :as pet :refer [pet]]
    [uix.dom]))

(def animals ["bird", "cat", "dog", "rabbit", "reptile"])
(def breeds [])

(defn request-pets [set-pets! animal location breed]
  (let [result (js/fetch (str "http://pets-v2.dev-apis.com/pets?animal=" animal "&location=" location "&breed=" breed))]
    (-> result
        (.then (fn [response]
                 (.json response)))
        (.then (fn [json-data]
                 ;;(js/console.log json-data)
                 (set-pets! (:pets (js->clj json-data :keywordize-keys true))))))))


(defui search-parameters [_]
  (let [[location set-location!] (uix/use-state "")
        [animal set-animal!]     (uix/use-state "")
        [breed set-breed!]       (uix/use-state "")
        [pets set-pets!]         (uix/use-state [])]
(println "this is pets" pets)
    (uix/use-effect
        (fn []
          (request-pets set-pets! animal location breed))
        [animal])

    ($ :div {:className "search-params"}
       ($ :form
          ($ :label {:htmlFor location}
            "Location"
            ($ :input {:id          "location"
                       :value       location
                       :placeholder "Location"
                       :onChange    #(set-location!   (.-value (.-target %)))}))

          ($ :label {:htmlFor animal}
            "Animal"
            ($ :select
               {:id          "animal"
                :value       animal
                :placeholder "Animal"
                :onChange    #(do
                                (set-animal! (.-value (.-target %)))
                                (set-breed! "")
                                )}
               (concat [($ :option {:value nil} nil)]
                       (map (fn [animal] ($ :option {:key animal :value animal} animal)) animals))))

          ($ :label {:htmlFor breed}
            "Breed"
            ($ :select
               {:id          "breed"
                :disabled    (empty? breed);;(= (count breeds) 0)
                :value       breed
                :placeholder "Breed"
                :onChange    #(set-breed!   (.-value (.-target %)))}
               (map (fn [breed] ($ :option {:key breed :value breed} breed)) breeds)))

          ($ :button "Submit"))

       (map (fn [dier]
              ($ pet {:name (:name dier) :breed (:breed dier) :animal (:animal dier) :key (:id dier)}))
            pets)
       )))
