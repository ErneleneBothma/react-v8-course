(ns app.details
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    ["react-router-dom" :as p  :refer [useParams]]
    ["@tanstack/react-query" :refer [useQuery]]
    [app.fetch-pets :as fetch-pets :refer [fetch-pets]]))

(defui details [_]
(let [pet-id (.-id (useParams))
      result (useQuery (clj->js ["details" pet-id]) fetch-pets)]
  (js/console.log "in details" result)
;;(js/console.log "is it loading?" (.-isLoading result))
;;(println "pets is " (js->clj (.-pets (.-data result)):keywordize-keys true))
 (if (.-isLoading result)
    ($ :div {:className "loading-pane"}
       ($ :h2 {:className "loader"} "🌀"))
    (let [pet (first (js->clj (.-pets (.-data result)):keywordize-keys true))]
      (println  "this is PET " pet)
      ($ :div {:className "details"}
       ($ :div
          ($ :h1 (:name pet))
          ($ :h2 (str (:animal pet) " - " (:breed pet) " - " (:city pet) " - " (:state pet))
          ($ :button (str "Adopt " (:name pet)))
          ($ :p (:description pet)))))))))
