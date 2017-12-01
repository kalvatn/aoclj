(ns aoc.2017.01-test
  (:require [clojure.test :refer :all]
            [aoc.2017.01 :refer :all]))

(deftest test-parse-input
  (is (= [1 1 2 2] (parse-input "1122")))
  (is (= [1 1 1 1] (parse-input "1111")))
  (is (= [1 2 3 4] (parse-input "1234")))
  (is (= [9 1 2 1 2 1 2 9] (parse-input "91212129"))))

(deftest test-group-digits
  (is (= [[1 1] [2 2]] (group-digits-part-one [1 1 2 2])))
  (is (= [[1 1] [1 1] [1 1]] (group-digits-part-one [1 1 1 1])))
  )

(deftest test-sum-digits
  (is (= 3 (sum-digits-part-one [1 1 2 2])))
  (is (= 4 (sum-digits-part-one [1 1 1 1])))
  (is (= 0 (sum-digits-part-one [1 2 3 4])))
  (is (= 9 (sum-digits-part-one [9 1 2 1 2 1 2 9])))
  )

(deftest test-get-halfway-index
  (is (= 2 (get-halfway-index 0 4)))
  (is (= 3 (get-halfway-index 1 4)))
  )

(deftest test-lookup-halfway
  (is (= 1 (lookup-halfway [1 2 1 2] 0)))
  (is (= 2 (lookup-halfway [1 2 1 2] 1)))
  (is (= 1 (lookup-halfway [1 2 1 2] 2)))
  (is (= 2 (lookup-halfway [1 2 1 2] 3)))
  )

(deftest test-sum-digits-part-two
  (is (= 6 (sum-digits-part-two [1 2 1 2])))
  (is (= 0 (sum-digits-part-two [1 2 2 1])))
  (is (= 4 (sum-digits-part-two [1 2 3 4 2 5])))
  (is (= 12 (sum-digits-part-two [1 2 3 1 2 3])))
  (is (= 4 (sum-digits-part-two [1 2 1 3 1 4 1 5])))
  )

(deftest test-part-one)
(deftest test-part-two)
