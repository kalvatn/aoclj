(ns aoc.core.math-test
  (:require [clojure.test :refer :all]
            [aoc.core.math :refer :all]))

(deftest test-combinations-of-size
  (is (= [[1 1 1] [1 1 2] [1 2 2]] (combinations-of-size [1 1 1 2 2] 3)))
  (is (= [[1 2 3 4 5]] (combinations-of-size [1 2 3 4 5] 5))))

(deftest test-all-combinations
  (is (= [[1] [2] [3] [1 2] [1 3] [2 3] [1 2 3] ] (all-combinations [1 2 3]))))

