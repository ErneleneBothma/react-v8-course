(ns app.carousel
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]))

(defui carousel
  {:statics {;defaultProps {:images ["http://pets-images.dev-apis.com/pets/none.jpg"]}}}
  (defstate active 0))))



(defn create-carousel []
  (uix/create-element carousel))

(defn render-carousel [element target]
  (uix/render element target))
