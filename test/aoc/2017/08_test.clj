(ns aoc.2017.08-test
  (:require [clojure.test :refer :all]
            [aoc.2017.08 :refer :all]))

(deftest test-regex
  (is (=(count input) (count (remove false? (map (partial re-matches r) input)))))
  )

(deftest test-parse-line
  (is (= ['b 'inc 5 'a '> 1] (parse-line "b inc 5 if a > 1")))
  (is (= ['a 'inc 1 'b '< 5] (parse-line "a inc 1 if b < 5")))
  )

(deftest test-parse-input
  ; (is (= [] (parse-input "")))
  ; (is (= [] (parse-input [""])))
  )

(deftest test-part-one
  ; (part-one input)
  (part-one ["b inc 5 if a > 1"
             "a inc 1 if b < 5"
             "c dec -10 if a >= 1"
             "c inc -20 if c == 10"])
  )
(deftest test-part-two)
