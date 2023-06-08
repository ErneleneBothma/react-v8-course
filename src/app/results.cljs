(ns app.results
  (:require
    [uix.core :as uix :refer [defui $]]
    [app.pet :as pet :refer [pet]]
        [uix.dom]))

(defui results [{:keys [pets]}]
  ($ :div  {:className "search"}
     ;;(println "this is pets in results" pets)
     (if (empty? pets)
       ($ :h1 "No Pets Found")
       (map (fn [dier]
              ($ pet {:name (:name dier)
                      :breed (:breed dier)
                      :animal (:animal dier)
                      :key (:id dier)
                      :id (:id dier)
                      :images (:images dier)
                      :location (str (:city dier) (:state dier))}))
            pets))))
