(ns app.details
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom :as dom]
    ["react-router-dom" :as p  :refer [useParams useNavigate]]
    ["@tanstack/react-query" :refer [useQuery]]
    [app.fetch-pets :as fetch-pets :refer [fetch-pets]]
    [app.modal :refer [modal]]
    [app.adopted-pet-context :refer [adopted-pet-context]]))

(defui details [_]
  (let [pet-id                       (.-id (useParams))
        result                       (useQuery (clj->js ["details" pet-id]) fetch-pets)
        [show-modal set-show-modal!] (uix/use-state false)
        navigate                     (useNavigate)
        [_ set-adopted-pet!]         (uix/use-context adopted-pet-context)]

 (if (.-isLoading result)
   ($ :div {:className "loading-pane"}
      ($ :h2 {:className "loader"} "🌀"))
   (let [pet (first (js->clj (.-pets (.-data result)):keywordize-keys true))]
      ;;(println  "this is PET " pet)
     ($ :div {:className "details"}
       ($ :div
          ($ :h1 (:name pet))
          ($ :h2 (str (:animal pet) " - " (:breed pet) " - " (:city pet) " - " (:state pet)))
          ($ :button {:onClick #(set-show-modal! true) }
             (str "Adopt " (:name pet)))
          ($ :p (:description pet))
          (when show-modal
            ;;(println show-modal )
          ($ modal
             ($ :div
                ($ :h1 (str "Would you like to adopt" (:name pet)))
                ($ :div {:className "buttons"}
                   ($ :button {:onClick #(do
                                           (set-adopted-pet! pet)
                                           (navigate "/"))}
                      "Yes")
                   ($ :button {:onClick #(set-show-modal! false)}
                      "No")))))))))))
