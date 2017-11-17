(ns aoc.2015.4_test
  (:require [clojure.test :refer :all]
            [aoc.core :refer :all]
            [aoc.2015.4 :refer :all]))

(deftest test-first-five
  (is (= "abcde" (first-five "abcdefghjklimnop"))))

(deftest test-five-zeroes
  (is (= true (starts-with-five-zeroes "00000")))
  (is (= true (starts-with-five-zeroes "000001dbbfa3a5c83a2d506429c7b00e")))
  (is (= false (starts-with-five-zeroes "12345")))
  (is (= true (starts-with-five-zeroes "000001dbbfa"))))

(deftest test-part-one
  (is (= 609043 (part-one "abcdef")))
  (is (= 1048970 (part-one "pqrstuv")))
  (is (= true (number? (part-one)))))

(deftest test-part-two
  (is (= true (number? (part-two)))))
