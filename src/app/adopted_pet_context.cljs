(ns app.adopted-pet-context
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom :as dom]))

(def adopted-pet-context
  (uix/create-context [] ))
