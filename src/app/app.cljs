(ns app.app
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    ["react-router-dom" :refer [BrowserRouter Routes Route Link]]
    ["@tanstack/react-query" :refer [QueryClient QueryClientProvider]]
    [app.details :as details :refer [details]]
    [app.search-params :as search-params :refer [search-parameters]]))

(def query-client
  (QueryClient.
   {:defaultOptions
    {:queries {:staleTime js/Infinity
               :cacheTime js/Infinity}}}))

(defui app []
($ :div

      ($ BrowserRouter
         ($ QueryClientProvider {:client query-client}
            ($ :header
               ($ Link {:to "/"} "Adopt Me!" ))
            ($ Routes
               ($ Route {:path "/details/:id" :element [($ details)]})
               ($ Route {:path "/" :element [($ search-parameters)]})))
         ))
  )


(defonce root
  (uix.dom/create-root (js/document.getElementById "root")))

(defn render []
  (uix.dom/render-root ($ app) root))

(defn ^:export init []
  (render))
