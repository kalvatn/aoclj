(ns aoc.2015.02_test
  (:require [clojure.test :refer :all]
            [aoc.2015.02 :refer :all]))

(deftest test-parse-line
  (is (= [2 3 4] (parse-line "2x3x4"))))

(deftest test-calc-box-paper
  (let [result1 (calc-box-paper [2 3 4])
        result2 (calc-box-paper [1 1 10])]
    (is (= 58 result1))
    (is (= 43 result2))))

(deftest test-calc-box-ribbon
  (let [result1 (calc-box-ribbon [2 3 4])
        result2 (calc-box-ribbon [1 1 10])]
    (is (= 34 result1))
    (is (= 14 result2))))
