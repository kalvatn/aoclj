(ns aoc.2015.1_test
  (:require [clojure.test :refer :all]
            [aoc.2015.1 :refer :all]))

(deftest test-map-up-down
  (is (= [1 1 -1 -1 -1 -1] (map-up-down "(())))"))))

(deftest test-part-one
  (is (= 1 (part-one "(")))
  (is (= 2 (part-one "((")))
  (is (= 0 (part-one "()")))
  (is (= -1 (part-one "())")))
  (is (= 0 (part-one ")(")))
  (is (= 4 (part-one "((((")))
  (is (= 4 (part-one "((())((("))))

(deftest test-part-two
  (is (= 1 (part-two ")")))
  (is (= 5 (part-two "(()))")))
  (is (= 7 (part-two "()()())"))))

