(ns aoc.2017.12-test
  (:require [clojure.test :refer :all]
            [aoc.2017.12 :refer :all]))

(deftest test-parse-line
  (is (= #{[0 1]
           [1 0]} (parse-line "0 <-> 1")))
  (is (= #{[0 1]
           [1 0]
           [0 2]
           [2 0]} (parse-line "0 <-> 1,2")))
  (is (= #{[4 2]
           [2 4]
           [4 3]
           [3 4]
           [4 6]
           [6 4]} (parse-line "4 <-> 2, 3, 6")))
  )

(deftest test-part-one)
(deftest test-part-two)
