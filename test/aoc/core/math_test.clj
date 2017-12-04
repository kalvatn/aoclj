(ns aoc.core.math-test
  (:require [clojure.test :refer :all]
            [aoc.core.math :as math]))

(deftest test-all-combinations
  (is (= [[1] [2] [3] [1 2] [1 3] [2 3] [1 2 3] ] (math/all-combinations [1 2 3]))))

