(ns aoc.2015.16-test
  (:require [clojure.test :refer :all]
            [aoc.2015.16 :refer :all]))

(deftest test-sanitize-input
  (is (= "{number 1 children 1 cars 8 vizslas 7}" (sanitize-input "Sue 1: children: 1, cars: 8, vizslas: 7"))))

(deftest test-parse-input
  (is (= {:number 1 :children 1 :cars 8 :vizslas 7} (parse-input "Sue 1: children: 1, cars: 8, vizslas: 7"))))

(deftest test-clue-fn-part-one
  (is (true? (clue-fn-part-one {:number 1 :cats 7} :cats)))
  (is (true? (clue-fn-part-one {:number 1 :cars 0} :cats))))

(deftest test-clue-fn-part-two
  (is (false? (clue-fn-part-two {:cats 7} :cats)))
  (is (false? (clue-fn-part-two {:trees 3} :trees)))
  (is (true? (clue-fn-part-two {:cats 8} :cats)))
  (is (true? (clue-fn-part-two {:trees 4} :trees)))
  (is (false? (clue-fn-part-two {:pomeranians 3} :pomeranians)))
  (is (false? (clue-fn-part-two {:goldfish 5} :goldfish)))
  (is (true? (clue-fn-part-two {:pomeranians 2} :pomeranians)))
  (is (true? (clue-fn-part-two {:goldfish 2} :goldfish)))
  (is (true? (clue-fn-part-two {:cars 0} :cats))))

(deftest test-matches-clues
  (is (= (repeat (count clues) true) (matches-clues clues clue-fn-part-one))))


(deftest test-part-one)
(deftest test-part-two)
