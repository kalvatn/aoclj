(ns aoc.core.ext-test
  (:require  [clojure.test :refer :all]
            [aoc.core.ext :refer :all]))

(deftest test-any?
  (is (= true (any? true? [true true false])))
  (is (= true (any? number? [1 2 3])))
  (is (= false (any? number? ["a" "b" "c"])))
  (is (= true (any? number? [1 "a" 3])))
  (is (= false (any? true? [false false false]))))
