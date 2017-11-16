(ns aoc.2015.1_test
  (:require [clojure.test :refer :all]
            [aoc.core :refer :all]
            [aoc.2015.1 :refer :all]))

(deftest test-one
  (is (= 138 (one))))

(deftest test-map-up-down
  (is (= [1 1 -1 -1 -1 -1] (map-up-down "(())))"))))

(deftest test-two
  (let [result (two)]
    (is (= 1771 result))
    (is (= true (number? result)))))

