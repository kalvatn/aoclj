(ns aoc.2017.03-test
  (:require [clojure.test :refer :all]
            [aoc.2017.03 :refer :all]))

(deftest test-oob
  (is (true? (oob [3 3] 3)))
  (is (true? (oob [-1 3] 3)))
  (is (true? (oob [0 3] 3)))
  (is (true? (oob [0 -1] 3)))
  )

(deftest test-next-pos
  (is (= [1 2] (next-pos 1 1 [0 1])))
  )

(deftest test-gen
  (is (= [[1 1 1]
          [1 2 2]
          [0 2 3]
          [0 1 4]
          [0 0 5]
          [1 0 6]
          [2 0 7]
          [2 1 8]
          [2 2 9]] (gen 3))))

; (deftest test-gen
;   (is (= [[2 2 1]
;           [2 3 2]
;           [1 3 3]
;           [1 2 4]
;           [1 1 5]
;           [2 1 6]
;           [3 1 7]
;           [3 2 8]
;           [3 3 9]
;           [3 4 10]
;           [2 4 11]
;           [1 4 12]
;           [0 4 13]
;           [0 3 14]
;           [0 2 15]
;           [0 1 16]
;           [0 0 17]
;           ] (gen 4))))

(deftest test-generate-number-spiral
  (is (= [[5 4 3]
          [6 1 2]
          [7 8 9] ] (number-spiral 3))))
  ; (is (= [[17  16  15  14  13]
  ;         [18   5   4   3  12]
  ;         [19   6   1   2  11]
  ;         [20   7   8   9  10]
  ;         [21  22  23  24  25]] (number-spiral 5))))

(deftest test-sum-neighbours
  (let [m [[1 1 1]
           [1 0 1]
           [1 1 1]]]
    (is (= 8 (sum-neighbours m 1 1)))
    (is (= 2 (sum-neighbours m 0 0)))
    (is (= 2 (sum-neighbours m 2 2)))
    (is (= 4 (sum-neighbours m 1 2)))
    ))

(deftest test-part-one)
(deftest test-part-two)
