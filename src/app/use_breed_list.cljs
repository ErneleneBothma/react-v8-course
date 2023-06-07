(ns app.use-breed-list
   (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]))
(def local-cache {})

(defn request-breed-list [animal set-breed-list! set-status! set-local-cache!]
  (let [result (js/fetch (str "http://pets-v2.dev-apis.com/breeds?animal=$" animal))
        json   (.then result (fn [response] (.json response)))]
  (set-breed-list! [])
  (set-status! "loading")
  (set-local-cache! (or json []))
  (set-breed-list! (get local-cache animal))
  (set-status! "loaded")))


(defn use-breed-list [animal]
  (let [[breed-list, set-breed-list!] (uix/use-state [])
        [status, set-status!]          (uix/use-state "unloaded")
        [local-cache set-local-cache!] (uix/use-state {})]
(uix/use-effect
(cond
  (empty? animal)          (set-breed-list! [])
  (get local-cache animal) (set-breed-list! (get local-cache animal))
  :else                    (request-breed-list animal set-breed-list! set-status! set-local-cache!))
[animal])
[breed-list status]))
