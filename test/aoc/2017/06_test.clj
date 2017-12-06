(ns aoc.2017.06-test
  (:require [clojure.test :refer :all]
            [aoc.2017.06 :refer :all]))


(deftest test-parse-input
  (is (= [1 2 3 4] (parse-input "1\t2\t3\t4")))
  (is (= [0 1 2 3] (parse-input "0 1 2 3")))
  )

(deftest test-get-highest
  (is (= [2 7] (get-highest [0 2 7 0])))
  (is (= [3 10] (get-highest [0 0 0 10 7 1])))
  (is (= [0 3] (get-highest [3 1 2 3])))
  )

(deftest test-cycle-indices
  (is (= [0 1 2 3] (cycle-indices 0 4 (range 0 4))))
  (is (= [1 2 3 0] (cycle-indices 1 4 (range 0 4))))
  (is (= [3 0 1 2 3 0 1 2 3 0] (cycle-indices 3 10 (range 0 4))))
  )

(deftest test-redistribute
  (is (= [2 4 1 2] (redistribute [0 2 7 0])))
  (is (= [3 1 2 3] (redistribute [2 4 1 2])))
  (is (= [0 2 3 4] (redistribute [3 1 2 3])))
  )

(deftest test-index-increment-count
  (is (= {0 0, 1 2, 2 2, 3 0} (index-increment-count [1 1 2 2] 4))))

(deftest test-cycle-redistribution
  (is (= [5 [2 4 1 2] 4] (cycle-redistribution [0 2 7 0])))
  )
(deftest test-part-one
  (is (= 5 (part-one "0 2 7 0"))))

(deftest test-part-two
  (is (= 4 (part-two "0 2 7 0"))))
