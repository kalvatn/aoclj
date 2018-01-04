(ns aoc.2017.17-test
  (:require [clojure.test :refer :all]
            [aoc.2017.17 :refer :all]))

(deftest test-spin
  (is (= [[0 1] 1 3] (vals (spin (->Buffer [0] 0 3) 1))))
  (is (= [[0 2 1] 1 3] (vals (spin (->Buffer [0 1] 1 3) 2))))
  (is (= [[0 2 3 1] 2 3] (vals (spin (->Buffer [0 2 1] 1 3) 3))))
  )

(deftest test-part-one)
(deftest test-part-two)
