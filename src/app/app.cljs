(ns app.app
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    ["react-router-dom" :refer [BrowserRouter Routes Route Link]]
    [app.details :as details :refer [details]]
    [app.search-params :as search-params :refer [search-parameters] ]))

(defui app []
($ :div

      ($ BrowserRouter
         ($ :header ;;"Adopt Me!"
         ($ Link {:to "/"} "Adopt Me!" ))
        ($ Routes
          ($ Route {:path "/details/:id" :element [($ details)]})
          ($ Route {:path "/" :element [($ search-parameters)]}))))
  )


(defonce root
  (uix.dom/create-root (js/document.getElementById "root")))

(defn render []
  (uix.dom/render-root ($ app) root))

(defn ^:export init []
  (render))
