(ns aoc.2017.04-test
  (:require [clojure.test :refer :all]
            [aoc.2017.04 :refer :all]))


(deftest test-is-anagram?
  (is (true? (is-anagram? ["asdf" "fdsa"])))
  (is (true? (is-anagram? ["asdf" "fdsa"])))
  )

(deftest test-has-anagram?
  (is (true? (has-anagram? ["asdf" "fdsa"])))
  (is (false? (has-anagram? ["aa" "bb"]))))

(deftest test-part-one)
(deftest test-part-two)
