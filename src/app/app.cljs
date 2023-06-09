(ns app.app
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    ["react-router-dom" :refer [BrowserRouter Routes Route Link]]
    ["@tanstack/react-query" :refer [QueryClient QueryClientProvider]]
    [app.details :as details :refer [details]]
    [app.search-params :as search-params :refer [search-parameters]]
    [app.adopted-pet-context :refer [adopted-pet-context]]))

(def query-client
  (QueryClient.
   {:defaultOptions
    {:queries {:staleTime js/Infinity
               :cacheTime js/Infinity}}}))

(defui app []
  (let [adopted-pet (uix/use-state nil)]
    ($ :div

       ($ BrowserRouter
          ($ QueryClientProvider {:client query-client}
             ($ (.-Provider adopted-pet-context)
                {:value adopted-pet}
                ($ :header
                   ($ Link {:to "/"} "Adopt Me!" )))
             ($ Routes
                ($ Route {:path "/details/:id" :element [($ details)]})
                ($ Route {:path "/" :element [($ search-parameters)]})))
          )))
  )


(defonce root
  (uix.dom/create-root (js/document.getElementById "root")))

(defn render []
  (uix.dom/render-root ($ app) root))

(defn ^:export init []
  (render))
