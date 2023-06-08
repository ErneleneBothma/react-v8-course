(ns app.use-breed-list
   (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
     ["@tanstack/react-query" :refer [useQuery]]
    [app.fetch-breed-list :as fetch-breed-list :refer [fetch-breed-list]]))


(defn use-breed-list [animal]
  (let [ result (useQuery (clj->js ["details" animal]) fetch-breed-list)
        data (js->clj (.-data result))
        status (.-status result)]
    ;;(js/console.log "BREED" result)
    [(or (get-in data ["breeds"]) [])
            status]))

#_(defn request-breed-list [animal set-breed-list! set-status! local-cache set-local-cache!]
  (set-status! "loading")
  (let [result (js/fetch (str "http://pets-v2.dev-apis.com/breeds?animal=" animal))]
    (-> result
        (.then (fn [response]
                 (.json response)))
    (.then (fn [json]
             (let [json-data (js->clj json :keywordize-keys true)]
             (set-breed-list! (or (:breeds json-data) []))
             (set-local-cache! (assoc local-cache animal (:breeds json-data)))
             (set-status! "loaded")))))))



#_(defn use-breed-list [animal]
  (let [[breed-list, set-breed-list!] (uix/use-state [])
        [status, set-status!]          (uix/use-state "unloaded")
        [local-cache set-local-cache!] (uix/use-state {})]
(uix/use-effect
(fn []
  (cond
  (empty? animal)          (set-breed-list! [])
  (get local-cache animal) (set-breed-list! (get local-cache animal))
  :else                    (request-breed-list animal set-breed-list! set-status! local-cache set-local-cache!)))
[animal])
[breed-list status]))
