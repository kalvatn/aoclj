(ns aoc.2015.08-test
  (:require [clojure.test :refer :all]
            [aoc.2015.08 :refer :all]))


(deftest test-part-one
  (is (= 2 (part-one [ "\"\"" ])))
  (is (= 2 (part-one [ "\"abc\"" ])))
  (is (= 3 (part-one [ "\"aaa\"aaa\"" ])))
  (is (= 5 (part-one [ "\"\\x27\"" ])))
  (is (= 12 (part-one [ "\"\"" "\"abc\"" "\"aaa\"aaa\"" "\"\\x27\"" ])))
  )
(deftest test-part-two
  (is (= (- 6 2) (part-two [ "\"\"" ])))
  (is (= (- 9 5) (part-two [ "\"abc\"" ])))
  ; (is (= (- 16 10) (part-two [ "\"aaa\"aaa\"" ])))
  (is (= (- 11 6) (part-two [ "\"\\x27\"" ])))
  ; (is (= (- 42 23) (part-two [ "\"\"" "\"abc\"" "\"aaa\"aaa\"" "\"\\x27\"" ])))
  )
