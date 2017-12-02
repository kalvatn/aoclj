(ns aoc.2017.02-test
  (:require [clojure.test :refer :all]
            [aoc.core.io :refer :all]
            [aoc.2017.02 :refer :all]))

(deftest test-parse-input
  (is (= [[5 1 9 5]
          [7 5 3]
          [2 4 6 8]] (parse-input (lines "2017/02_test.txt"))))
  )

(deftest test-row-diff
  (is (= 8 (row-diff-largest-smallest [5 1 9 5])))
  (is (= 8 (row-diff-largest-smallest [5 1 9 5])))
  )

(deftest test-evenly-divisible
  (is (true? (evenly-divisible? [9 3])))
  (is (false? (evenly-divisible? [9 2])))
  )

(deftest test-row-evenly-divisible
  (is (= [[8 2]] (row-evenly-divisible [ 5 9 2 8 ]))))

(deftest test-part-one
  (is (= 18 (part-one ["5 1 9 5" "7 5 3" "2 4 6 8"]))))
(deftest test-part-two
  (is (= 9 (part-two ["5 9 2 8" "9 4 7 3" "3 8 6 5"]))))
