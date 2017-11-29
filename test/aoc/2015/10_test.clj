(ns aoc.2015.10-test
  (:require [clojure.test :refer :all]
            [aoc.2015.10 :refer :all]))

(deftest test-look-and-say
  (is (= "11" (look-and-say "1" 1)))
  (is (= "21" (look-and-say "11" 1)))
  (is (= "1211" (look-and-say "21" 1)))
  (is (= "111221" (look-and-say "1211" 1)))
  (is (= "312211" (look-and-say "111221" 1)))
  (is (= "312211" (look-and-say "1" 5))))
