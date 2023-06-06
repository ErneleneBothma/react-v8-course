(ns app.search-params
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]))
(def animals ["bird", "cat", "dog", "rabbit", "reptile"])
(def breeds [])

(defui search-parameters [_]
  (let [[location set-location] (uix/use-state "")
        [animal set-animal]     (uix/use-state "")
        [breed set-breed]       (uix/use-state "")]
    ($ :div {:className "search-params"}
       ($ :form
          ($ :label {:htmlFor location}
            "Location"
            ($ :input {:id          "location"
                       :value       location
                       :placeholder "Location"
                       :onChange    #(set-location  )}))

          ($ :label {:htmlFor animal}
            "Animal"
            ($ :select
               {:id          "animal"
                :value       animal
                :placeholder "Animal"
                :onChange    #(set-animal  )}
               (map (fn [animal] ($ :option {:key animal :value animal} animal)) animals)))

          ($ :label {:htmlFor breed}
            "Breed"
            ($ :select
               {:id          "breed"
                :disabled    (= (count breed) 0)
                :value       breed
                :placeholder "Breed"
                :onChange    #(set-breed  )}
               (map (fn [breed] ($ :option {:key breed :value breed} breed)) breeds)))

          ($ :button "Submit")))))
