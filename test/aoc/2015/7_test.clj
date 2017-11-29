(ns aoc.2015.7_test
  (:require [clojure.test :refer :all]
            [aoc.2015.7 :refer :all]))

(deftest test-parse-instruction
  (is (= {:tokens '(123 -> x)
          :target 'x
          :command '(123)
          :operation nil} (parse-instruction "123 -> x")))
  (is (= {:tokens '(NOT y -> x)
          :target 'x
          :command '(NOT y)
          :operation 'NOT} (parse-instruction "NOT y -> x"))))


(def test-register { :x 123 :y 99 :z 2 })

(deftest test-lookup-register-value
  (is (= 123 (lookup-register-value test-register :x)))
  (is (= 99 (lookup-register-value test-register :y)))
  (is (= 2 (lookup-register-value test-register :z)))
  (is (= nil (lookup-register-value test-register :a))))

(deftest test-run-instruction
  (run-instruction {} "NOT x -> y")
  (run-instruction {} "123 -> x")
  (run-instruction {} "NOT x -> y"))


(deftest test-initialize-register
  (is (= {"x" "123"
          "y" "456"
          "d" "x AND y"
          "e" "x OR y"
          "f" "x LSHIFT 2"
          "g" "y RSHIFT 2"
          "h" "NOT x"
          "i" "NOT y"} (initialize-register ["123 -> x"
                                             "456 -> y"
                                             "x AND y -> d"
                                             "x OR y -> e"
                                             "x LSHIFT 2 -> f"
                                             "y RSHIFT 2 -> g"
                                             "NOT x -> h"
                                             "NOT y -> i"]))))
