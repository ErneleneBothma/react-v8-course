(ns app.pet
  (:require
    [uix.core :as uix :refer [defui $]]
    ["react-router-dom" :refer [Link]]
    [uix.dom]))

(defui pet [{:keys [name animal breed images location id]}]
  ;;(println "this is image" images)

  ($ :<>
     ($ Link {:to (str "/details/" id)
            :className "pet"})
  ($ :div  {:className "image-container"}
       ($ :img {:src (if (empty? images)
                    "http://pets-images.dev-apis.com/pets/none.jpg"
                    (first images))
             :alt name}))
    ($ :div {:className "info"}
      ($ :h1 name)
      ($ :h2 (str animal" - " breed " - " location))))

  )
