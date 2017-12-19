(ns aoc.2017.07-test
  (:require [clojure.test :refer :all]
            [aoc.core.ext :refer :all]
            [aoc.2017.07 :refer :all]))


(deftest test-parse-line
  (is (= ["pbga" 66 []] (parse-line "pbga (66)")))
  (is (= ["fwft" 72 ["ktlj" "cntj" "xhth"]] (parse-line "fwft (72) -> ktlj, cntj, xhth")))
  (is (= ["ugml" 68 ["gyxo" "ebii" "jptl"]] (parse-line "ugml (68) -> gyxo, ebii, jptl")))
  )

(deftest test-part-one)
(deftest test-part-two)
