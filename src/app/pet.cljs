(ns app.pet
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]))

(defui pet [{:keys [name animal breed images location id]}]
  (println "this is image" images)
  #_($ :a ;;{:href "" #_($ detaills id )}
     {:className "pet"}
     )
  ($ :div  {:className "pet"}
  ($ :div  {:className "img-container"}
       ($ :img {:src (if (empty? images)
                    "http://pets-images.dev-apis.com/pets/none.jpg"
                    (first images))
             :alt name}))
    ($ :div {:className "info"}
      ($ :h1 name)
      ($ :h2 (str animal" - " breed " - " location))))

  )
