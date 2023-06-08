(ns app.fetch-pets
  (:require
   [shadow.cljs.modern :refer [js-await]]))

(defn fetch-pets [context]
(js/console.log context)
  (let [query (.-queryKey context)
        id    (aget query 1)
        _     (println "id" id)]
    (js-await [fetch-result (js/fetch (str "http://pets-v2.dev-apis.com/pets?id=" id))]
              (js/console.log "my result" fetch-result #_(js->clj fetch-result))
                              (if (.-ok fetch-result)
                                 (.json fetch-result)
                                (js/Error. (str "details/" id " fetch not ok"))))))
