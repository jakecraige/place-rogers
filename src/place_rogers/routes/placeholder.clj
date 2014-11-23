(ns place-rogers.routes.placeholder
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [image-resizer.format :as format]
            [place-rogers.models.placeholder :as placeholder]
            [image-resizer.core :refer :all]))

(defn get-image [path]
  (io/file (io/resource path)))

(defn create-placeholder [[width height]]
  (let [width (Integer. width)
        height (Integer. height)
        image (get-image (placeholder/lookup-image-path [width height]))]
    (resize-and-crop image width height)))

(defn render-placeholder [image]
  (format/as-stream image "jpg"))

(defroutes placeholder-routes
  (GET "/placeholder/:width/:height" {{width :width height :height} :params}
       (-> (create-placeholder [width height])
           (render-placeholder))))
