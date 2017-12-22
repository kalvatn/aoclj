(ns aoc.2017.11-test
  (:require [clojure.test :refer :all]
            [aoc.2017.11 :refer :all]))


(deftest test-parse-input
  (is (= [:n :ne :sw] (parse-input "n,ne,sw")))
  (is (= [:a :b :c] (parse-input "a,b,c")))
  )


(deftest test-move
  (is (.equals {:x 0  :y 1  :z -1} (move start :n)))
  (is (.equals {:x 0  :y -1 :z 1} (move start :s)))

  (is (.equals {:x 1  :y 0  :z -1} (move start :ne)))
  (is (.equals {:x -1 :y 0  :z 1} (move start :sw)))

  (is (.equals {:x -1 :y 1  :z 0} (move start :nw)))
  (is (.equals {:x 1  :y -1 :z 0} (move start :se)))
  )

(deftest test-part-one)
(deftest test-part-two)
