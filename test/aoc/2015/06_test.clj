(ns aoc.2015.06_test
  (:require [clojure.test :refer :all]
            [aoc.2015.06 :refer :all]))

(deftest test-part-one
  (is (= (* 3 3) (part-one [ "toggle 0,0 through 2,2" ] 3)))
  (is (= (* 10 10) (part-one [ "toggle 0,0 through 9,9" ] 10)))
  (is (= (* 100 100) (part-one [ "toggle 0,0 through 99,99" ] 100))))

(deftest test-part-two
  (is (= (* 10 10 2) (part-two [ "toggle 0,0 through 9,9" ] 10)))
  (is (= (* 100 100 2) (part-two [ "toggle 0,0 through 99,99" ] 100))))
