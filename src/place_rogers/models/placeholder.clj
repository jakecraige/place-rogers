(ns place-rogers.models.placeholder)

(def possible-images [{:width 200 :height 240}
                      {:width 284 :height 300}
                      {:width 320 :height 240}
                      {:width 439 :height 618}
                      {:width 550 :height 525}
                      {:width 560 :height 420}
                      {:width 570 :height 618}
                      {:width 1024 :height 768}
                      {:width 1422 :height 2051}
                      {:width 1536 :height 1025}])

(defn image-diff
  "Returns the sum of the difference of an image and a width/height.
  0 means no difference"
  [[width height] img] (let [w-diff (- width (:width img))
                             h-diff (- height (:height img))
                             diff (+ w-diff h-diff)]
                         (Math/abs diff)))

(defn compare-images
  "Returns the image closest in size to the width/height"
  [dims img1 img2]
  (let [img1diff (image-diff dims img1)
        img2diff (image-diff dims img2)]
    (if (> img1diff img2diff)
      img2
      img1)))

(defn path-to-image [image]
  (str "roger_images/" (:width image) "x" (:height image) ".jpg"))

(defn lookup-image [dims]
  "takes image dimensions and returns the closest image in size"
  (loop [images possible-images image (first possible-images)]
    (if (empty? images) 
      image
      (let [next-image (compare-images dims image (first images))]
        (recur (rest images) next-image)))))

(defn lookup-image-path [dims]
  (path-to-image (lookup-image dims)))
