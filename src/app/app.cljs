(ns app.app
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    [uix.router :as router :refer [defroute $]]
    [app.details :as details :refer [details]]
    [app.search-params :as search-params :refer [search-parameters] ]))

(defui app []
 #_ ($ :div
        ($ :h1 "Adopt Me!")
        ($ search-parameters))
($ :div
      ($ :h1 "Adopt Me!")
      ($ router:browser-router
        ($ router:routes
          ($ router:route {:path "/details/:id" :element [details]})
          ($ router:route {:path "/" :element [search-parameters]}))))
  )


(defonce root
  (uix.dom/create-root (js/document.getElementById "root")))

(defn render []
  (uix.dom/render-root ($ app) root))

(defn ^:export init []
  (render))
