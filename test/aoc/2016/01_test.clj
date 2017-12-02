(ns aoc.2016.01-test
  (:require [clojure.test :refer :all]
            [aoc.2016.01 :refer :all]))

(deftest test-parse-input
  (is (= [ [:R 2] [:L 3]] (parse-input "R2, L3"))))

(deftest test-next-facing
  (is (= :W (next-facing :N :L)))
  (is (= :S (next-facing :E :R)))
  (is (= :S (next-facing :W :L)))
  )

(deftest test-next-pos
  (is (= [0 2 :E] (next-pos [0 0 :N] :R 2)))
  (is (= [3 2 :N] (next-pos [0 2 :E] :L 3)))

  (is (= [0 2 :E] (next-pos [0 0 :N] :R 2)))
  (is (= [-2 2 :S] (next-pos [0 2 :E] :R 2)))
  (is (= [-2 0 :W] (next-pos [-2 2 :S] :R 2)))
  )

(deftest test-get-distance
  (is (= 5 (get-distance [3 2 :N])))
  (is (= 2 (get-distance [-2 0 :W]))))

(deftest test-part-one)
(deftest test-part-two)
