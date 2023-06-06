(ns app.pet
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]))

(defui pet [props]
  ($ :div
      ($ :h2 (:name props))
      ($ :p "Animal: " (:animal props))
      ($ :p "Breed: " (:breed props))))
