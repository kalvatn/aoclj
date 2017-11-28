(ns aoc.core.io-test
  (:require [clojure.test :refer :all]
            [aoc.core.io :refer :all]))

(deftest test-lines
  (is (= ["abc 123 1234" "def" "ghj"] (lines "test.txt"))))

(deftest test-first-line
  (is (= "abc 123 1234" (first-line "test.txt"))))
