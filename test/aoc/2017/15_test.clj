(ns aoc.2017.15-test
  (:require [clojure.test :refer :all]
            [aoc.2017.15 :refer :all]))

(deftest test-parse-input
  (is (= [19 201] (parse-input ["Generator A starts with 19"
                                "Generator B starts with 201"]))))

(deftest test-part-one)
(deftest test-part-two)
