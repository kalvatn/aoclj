(ns aoc.2017.10-test
  (:require [clojure.test :refer :all]
            [aoc.2017.10 :refer :all]))

(deftest test-parse-input
  (is (= [3 4 1 5] (parse-input "3, 4, 1, 5"))))

(deftest test-select-range
  (is (= [0 1 2] (select-range [0 1 2 3 4] 0 3)))
  (is (= [3 4 2 1] (select-range [2 1 0 3 4] 3 4)))
  )

(deftest test-reverse-range
  (is (= [2 1 0] (reverse-range [0 1 2 3 4] 0 3)))
  )

(deftest test-knot
  (is (= [2 1 0 3 4] (knot [0 1 2 3 4] 0 3 0)))
  (is (= [4 3 0 1 2] (knot [2 1 0 3 4] 3 4 1)))
  (is (= [3 4 2 1 0] (knot [4 3 0 1 2] 1 5 4)))
  )

(deftest test-rotate
  (is (= [1 2 3 4 0] (rotate [0 1 2 3 4] 1)))
  (is (= [2 3 4 0 1] (rotate [0 1 2 3 4] 2)))
  (is (= [0 1 2 3 4] (rotate [0 1 2 3 4] 5)))
  )

(deftest test-part-one)
(deftest test-part-two)
