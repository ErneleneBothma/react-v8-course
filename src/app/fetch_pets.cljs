(ns app.fetch-pets)

(defn fetch-pets [query]
  #_(let [id     (aget query 1)
        result (js/fetch (str "http://pets-v2.dev-apis.com/pets?id=" id))]
    (-> result
        (.then (fn [response]
                 (.json response)))
    (.then (fn [json]
             (let [json-data (js->clj json :keywordize-keys true)]
               (if (.ok )
                 json-data
                 (js/Error. (str "details/" id " fetch not ok")))))))))
