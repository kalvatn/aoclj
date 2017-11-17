(ns aoc.2015.3_test
  (:require [clojure.test :refer :all]
            [aoc.core :refer :all]
            [aoc.2015.3 :refer :all]))

(deftest test-parse-input
  (is (= [ [-1 0] [0 -1] [0 1] [1 0]] (parse-input "<v^>"))))

(deftest test-part-one
  (is (= 2 (part-one ">")))
  (is (= 4 (part-one "^>v<")))
  (is (= 2 (part-one "^v^v^v^v^v")))
  (is (= 2572 (part-one))))

(deftest test-part-two
  (is (= 3 (part-two "^v")))
  (is (= 3 (part-two "^>v<")))
  (is (= 11 (part-two "^v^v^v^v^v")))
  (is (= true (number? (part-two)))))

