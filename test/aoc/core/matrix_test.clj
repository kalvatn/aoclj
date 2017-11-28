(ns aoc.core.matrix-test
  (:require [clojure.test :refer :all]
            [aoc.core.matrix :refer :all]))

(deftest test-pprint
  (pprint-matrix (vec-2d 3 3 0)))

(deftest test-vec-2d
  (is (= [[0 0 0]
          [0 0 0]
          [0 0 0]] (vec-2d 3 3 0)))
  (is (= [[true true true]] (vec-2d 1 3 true)))
  (is (= [["abc" "abc" "abc"]
          ["abc" "abc" "abc"]] (vec-2d 2 3 "abc")))
  (is (= 100 (count (vec-2d 100 1 0))))
  (is (= 1 (count (vec-2d 1 100 0))))
  (is (= 100000 (count (vec-2d 100000 100 0)))))

(deftest test-lookup
  (is (= 0 (lookup (vec-2d 3 3 0) 0 0)))
  (is (= 0 (lookup (vec-2d 100000 100000 0) 999 9999))))

(deftest test-assign
  (let [matrix (vec-2d 100000 100000 0)
        after (assign matrix [99128 12345 "abc"])]
    (is (= "abc" (lookup after 99128 12345)))))

(deftest test-assign-range
  (let [matrix (vec-2d 10 10 0)
        after (assign-range matrix [0 0 0 9 "abc"])]
    (is (= (vec (repeat 10 "abc")) (row after 0))))
  (let [matrix (vec-2d 3 3 0)
        after (assign-range matrix [0 0 2 2 1])]
    (is (= [[1 1 1]
            [1 1 1]
            [1 1 1]] after)))
  (let [matrix (vec-2d 4 4 0)
        t1 (assign-range matrix [0 0 3 3 :x])
        t2 (assign-range t1 [1 1 2 2 :y])
        t3 (assign-range t2 [1 0 2 0 :z])
        t4 (assign-range t3 [3 3 3 3 :last])
        ]
    (is (= [[:x :x :x :x]
            [:z :y :y :x]
            [:z :y :y :x]
            [:x :x :x :last]] t4)))
  (let [matrix (vec-2d 10000 10 0)
        t1 (assign-range matrix [0 0 3 3 :x])
        ]
    (is (= [:x :x :x :x 0 0 0 0 0 0] (row t1 0)))
    (is (= [:x :x :x :x 0 0 0 0 0 0] (row t1 1)))
    (is (= [:x :x :x :x 0 0 0 0 0 0] (row t1 2)))
    (is (= [:x :x :x :x 0 0 0 0 0 0] (row t1 3)))))

(deftest test-row
  (is (= [0 0 0 0 0] (row (vec-2d 1000000 5 0) 2))))

(deftest test-assign-with-fn
  (let [matrix (vec-2d 1 3 0)
        t1 (assign-with-fn matrix [0 0 inc])
        t2 (assign-with-fn t1 [0 0 inc])]
    (is (= [[1 0 0]] t1))
    (is (= [[2 0 0]] t2))))

(deftest test-assign-range-with-fn
  (let [matrix (vec-2d 3 3 0)
        t1 (assign-range-with-fn matrix [1 1 2 2 inc])
        t2 (assign-range-with-fn t1 [0 0 2 2 inc])]
    (is (= [[1 1 1]
            [1 2 2]
            [1 2 2]] t2)))
  (let [matrix (vec-2d 3 3 "abc")
        t1 (assign-range-with-fn matrix [1 1 2 2 clojure.string/reverse])
        ]
    (is (= [["abc" "abc" "abc" ]
            ["abc" "cba" "cba" ]
            ["abc" "cba" "cba" ]] t1)))
  (let [matrix (vec-2d 3 3 false)
        on (assign-range-with-fn matrix [0 0 2 2 (fn [v] true)])
        off (assign-range-with-fn matrix [0 0 2 2 (fn [v] false)])
        toggled (assign-range-with-fn [[false true false]
                                       [false false false]
                                       [true true false]] [0 0 2 2 not])]
    (is (= [[true true true]
            [true true true]
            [true true true]] on))
    (is (= [[false false false]
            [false false false]
            [false false false]] off))
    (is (= [[true false true]
            [true true true]
            [false false true]] toggled))))


