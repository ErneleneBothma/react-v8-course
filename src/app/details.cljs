(ns app.details
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    ["react-router-dom" :as p  :refer [useParams]]
    ["@tanstack/react-query" :refer [useQuery]]
    [app.fetch-pet :as fetch-pet :refer [fetch-pet]]))

(defui details [_]
(let [result ($ useQuery {:details :id} (fetch-pet))]
  (js/console.log ($ useParams))
  ($ :h2 "hi")))
