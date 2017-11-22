(ns aoc.2015.6_test
  (:require [clojure.test :refer :all]
            [aoc.core :refer :all]
            [aoc.2015.6 :refer :all]))


(deftest test-parse-instruction
  (is (= [TURN_ON, 0, 0, 999, 999] (parse-instruction "turn on 0,0 through 999,999")))
  (is (= [TOGGLE, 0, 0, 999, 0] (parse-instruction "toggle 0,0 through 999,0")))
  (is (= [TURN_OFF, 499, 499, 500, 500] (parse-instruction "turn off 499,499 through 500,500"))))

(deftest test-parse-multiple-instruction
  (is (= [[TOGGLE 0 0 2 2] [TOGGLE 0 0 2 2]] (parse-multiple-instruction [ "toggle 0,0 through 2,2" "toggle 0,0 through 2,2" ]))))

(deftest test-create-matrix
  (is (= [[0 0 0]
          [0 0 0]
          [0 0 0]] (create-matrix 3))))

(deftest test-state
  (is (= ON (state [[0 1 0]] 0 1)))
  (is (= OFF (state [[0 1 0]] 0 0)))
  (is (= OFF (state [[0 1 0]] 0 2)))
  (is (= ON (state [[0 0 0] [0 0 0] [1 0 0]] 2 0)))
  (is (= ON (state [[0 0 0] [0 1 0] [0 0 0]] 1 1))))

(deftest test-grid-action
  (is (= [[0 0 0]
          [0 1 0]
          [0 0 0]] (grid-action (create-matrix 3) [TURN_ON 1 1])))
  (is (= [[0 0 0]
          [0 0 0]
          [0 0 0]] (grid-action [[0 0 0] [0 1 0] [0 0 0]] [TOGGLE 1 1])))
  (is (= [[0 0 0]
          [0 0 0]
          [0 0 0]] (grid-action [[0 0 0] [0 1 0] [0 0 0]] [TURN_OFF 1 1]))))

(deftest test-grid-action-part-two
  (is (= [[0 0 0]
          [0 1 0]
          [0 0 0]] (grid-action-part-two (create-matrix 3) [TURN_ON 1 1])))
  (is (= [[0 0 0]
          [0 3 0]
          [0 0 0]] (grid-action-part-two [[0 0 0] [0 1 0] [0 0 0]] [TOGGLE 1 1])))
  (is (= [[0 0 0]
          [0 0 0]
          [0 0 0]] (grid-action-part-two [[0 0 0] [0 1 0] [0 0 0]] [TURN_OFF 1 1]))))

(deftest test-create-range-action
  (is (= [[TURN_ON 0 0] [TURN_ON 0 1] [TURN_ON 0 2]
          [TURN_ON 1 0] [TURN_ON 1 1] [TURN_ON 1 2]
          [TURN_ON 2 0] [TURN_ON 2 1] [TURN_ON 2 2]] (create-range-actions TURN_ON 0 0 2 2))))

(deftest test-range-action
  (is (= [[0 0 0]
          [1 1 1]
          [0 0 0]] (range-action (create-matrix 3) TURN_ON 1 0 1 2)))
  (is (= [[0 0 0]
          [0 1 1]
          [0 1 1]] (range-action (create-matrix 3) TURN_ON 1 1 2 2))))

(deftest test-count-on
  (is (= 4 (count-on [[0 0 0]
                      [0 1 1]
                      [0 1 1]]))))
(deftest test-sum-brightness
  (is (= 4 (sum-brightness [[0 0 0]
                            [0 1 1]
                            [0 1 1]])))
  (is (= 5 (sum-brightness [[0 0 0]
                            [0 2 1]
                            [0 1 1]])))
  (is (= 9 (sum-brightness [[1 1 1]
                            [1 1 1]
                            [1 1 1]]))))


(deftest test-part-one
  (is (= [[0 0 0]
          [0 0 0]
          [0 0 0]] (part-one [ "toggle 0,0 through 2,2" "toggle 0,0 through 2,2" ] 3)))
  (is (= [[1 1 1]
          [1 1 1]
          [1 1 1]] (part-one [ "toggle 0,0 through 2,2" ] 3))))

(deftest test-part-two
  (is (= [[3 3 3]
          [3 3 3]
          [3 3 3]] (part-two [ "toggle 0,0 through 2,2" "toggle 0,0 through 2,2" "turn off 0,0 through 2,2" ] 3))))
