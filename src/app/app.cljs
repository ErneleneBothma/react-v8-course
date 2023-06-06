(ns app.app
  (:require
     [uix.core :as uix :refer [defui $]]
    [uix.dom]
    [app.pet :as pet :refer [pet]]
    [app.search-params :as search-params :refer [search-parameters] ]))

(defui app []
  ($ :div
        ($ :h1 "Adopt Me!")
($ search-parameters)
        ($ pet {:name "Flappie" :breed "Labrador" :animal "Dog"})))


(defonce root
  (uix.dom/create-root (js/document.getElementById "root")))

(defn render []
  (uix.dom/render-root ($ app) root))

(defn ^:export init []
  (render))
