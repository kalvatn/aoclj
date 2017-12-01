(ns aoc.2015.17-test
  (:require [clojure.test :refer :all]
            [aoc.2015.17 :refer :all]))

(deftest test-parse-input
  (is (= [1 2 3] (parse-input ["1" "2" "3"]))))


(deftest test-combinations-with-sum
  (is (= [[1 3]] (combinations-with-sum [1 2 3] 4)))
  (is (= [[15 10] [20 5] [20 5] [15 5 5]] (combinations-with-sum [20 15 10 5 5] 25))))

(deftest test-count-combinations
  (is (= 4 (count-combinations [20 15 10 5 5] 25))))

(deftest test-part-one)
(deftest test-part-two)
