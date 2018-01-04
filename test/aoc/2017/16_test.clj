(ns aoc.2017.16-test
  (:require [clojure.test :refer :all]
            [aoc.2017.16 :refer :all]))

(deftest test-parse-token
  (is (= [ :spin 1 ] (parse-token "s1")))
  (is (= [ :exchange 1 2 ] (parse-token "x1/2")))
  (is (= [ :partner "a" "b" ] (parse-token "pa/b")))
  )

(deftest test-parse-input
  (is (= [[:spin 1]
          [:exchange 1 2]
          [:partner "a" "b"]] (parse-input "s1,x1/2,pa/b")))
  )

(deftest test-apply-spin
  (is (= "eabcd" (apply-spin "abcde" 1)))
  )

(deftest test-apply-exchange
  (is (= "eabdc" (apply-exchange "eabcd" 3 4)))
  )

(deftest test-apply-partner
  (is (= "baedc" (apply-partner "eabdc" "e" "b")))
  )

(deftest test-part-one)
(deftest test-part-two)
