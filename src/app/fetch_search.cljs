(ns app.fetch-search
  (:require
   [shadow.cljs.modern :refer [js-await]]))

(defn fetch-search [context]
;;(js/console.log "fetch context" context )
  (let [query (js->clj (.-queryKey context) :keywordize-keys true)
       [animal location breed] (vec (vals (second  query)))]

     (js-await [fetch-result (js/fetch (str "http://pets-v2.dev-apis.com/pets?animal=" animal "&location=" location "&breed=" breed))]
              ;;(js/console.log "my result" fetch-result #_(js->clj fetch-result))
                              (if (.-ok fetch-result)
                                 (.json fetch-result)
                                (js/Error. (str "details/" animal location breed " fetch not ok"))))))
