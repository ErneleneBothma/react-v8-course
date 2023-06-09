(ns  app.fetch-breed-list
  (:require
   [shadow.cljs.modern :refer [js-await]]))

(defn fetch-breed-list [context]
;;(js/console.log context)
  (let [query (.-queryKey context)
        animal    (aget query 1)]
    (if (empty? animal)
      []
    (js-await [fetch-result (js/fetch (str "http://pets-v2.dev-apis.com/breeds?animal=" animal))]
              ;;(js/console.log "my result" fetch-result #_(js->clj fetch-result))
                              (if (.-ok fetch-result)
                                 (.json fetch-result)
                                (js/Error. (str "details/" animal " fetch not ok")))))))
