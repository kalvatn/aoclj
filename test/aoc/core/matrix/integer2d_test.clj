(ns aoc.core.matrix.integer2d_test
  (:require [clojure.test :refer :all]
            [aoc.core.matrix.integer2d :as int2d]))

(deftest test-create
  (is (= [[0 0 0]
          [0 0 0]
          [0 0 0]] (map vec (int2d/create 3 3)))))

(deftest test-create-with-value-fn
  (is (= [[1 2 3]
          [4 5 6]
          [7 8 9]] (map vec (int2d/create-with-value-fn 3 3 #(iterate inc 1))))))

(deftest test-lookup
  (let [m (int2d/create-with-value-fn 3 3 #(iterate inc 1))]
    (is (= 5 (int2d/lookup m 1 1)))
    ))

(deftest test-assign
  (let [m (int2d/create-with-value-fn 3 3 #(iterate inc 1))]
    (is (= [[1 2 3]
            [4 5 6]
            [7 8 0]] (map vec (int2d/assign m [2 2 0]))))))
