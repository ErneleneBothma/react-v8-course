(ns app.details
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    ["react-router-dom" :as p  :refer [useParams]]
    ["@tanstack/react-query" :refer [useQuery]]
    [app.fetch-pets :as fetch-pets :refer [fetch-pets]]))

(defui details [_]
(let [result (useQuery {:details :id} fetch-pets)
      id (.-id (useParams))]
  (js/console.log (useParams))
  ($ :h2 id)))
