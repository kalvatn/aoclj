(ns aoc.2017.07-test
  (:require [clojure.test :refer :all]
            [aoc.core.ext :refer :all]
            [aoc.2017.07 :refer :all]))


(deftest test-parse-line
  (is (= ["pbga" 66 []] (parse-line "pbga (66)")))
  (is (= ["fwft" 72 ["ktlj" "cntj" "xhth"]] (parse-line "fwft (72) -> ktlj, cntj, xhth")))
  (is (= ["ugml" 68 ["gyxo" "ebii" "jptl"]] (parse-line "ugml (68) -> gyxo, ebii, jptl")))
  )

(deftest test-has-children?
  (is (= true (has-children? ["asdf" 1 ["abcd" "efgh"]]))))

(deftest test-get-parent
  (let [all [["a" 1 ["b" "c"]]
             ["b" 2 ["c"]]
             ["c" 3 []]]
        bparent (get-parent "b" all)
        ]

  ; (-pprint bparent)
  ; (is (= "a" bparent)))
  ))


(deftest test-part-one)
(deftest test-part-two)
