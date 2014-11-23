(ns place-rogers.test.models.placeholder
  (:use clojure.test
        place-rogers.models.placeholder))

(deftest test-placeholder-image-diff
  (testing "returns 0 when equal"
    (let [diff (image-diff [200 200] {:width 200 :height 200})]
      (is (= diff 0))))

  (testing "returns greater than 0 when not equal"
    (let [diff (image-diff [400 400] {:width 200 :height 200})]
      (is (= 400 diff)))))

(deftest test-placeholder-image-compare-images
  (testing "returns the closest image"
    (let [small-image {:width 200 :height 200}
          large-image {:width 1000 :height 1000}
          result (compare-images [300 300] small-image large-image)]
      (is (= result small-image))))

  (testing "returns the closest image"
    (let [small-image {:width 200 :height 200}
          large-image {:width 1000 :height 1000}
          result (compare-images [900 900] small-image large-image)]
      (is (= result large-image)))))

(deftest test-lookup-image
  (testing "lookup returns closest small image"
    (is (= (lookup-image [200 200]) {:width 200 :height 240})))

  (testing "lookup returns closest large image"
    (is (= (lookup-image [1000 1200]) {:width 1536 :height 1025}))))
