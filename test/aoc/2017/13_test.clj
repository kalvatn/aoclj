(ns aoc.2017.13-test
  (:require [clojure.test :refer :all]
            [aoc.2017.13 :refer :all]))

(deftest test-parse-input
  (is (= {0 3
          1 2
          4 4
          6 4} (parse-input ["0 : 3", "1 : 2", "4 : 4", "6 : 4"])))
  )

(deftest test-bounce-range
  (is (= [1 true] (bounce-range 0 3 true)))
  (is (= [2 true] (bounce-range 1 3 true)))
  (is (= [3 true] (bounce-range 2 3 true)))
  (is (= [2 false] (bounce-range 3 3 true)))
  )


(deftest test-part-one)
(deftest test-part-two)
