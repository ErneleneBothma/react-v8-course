(ns app.details
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    ["react-router-dom" :as p  :refer [useParams]]))

(defui details [_]
(js/console.log ($ useParams))
  ($ :h2 "hi"))
