(ns aoc.2015.1
  (:require [aoc.core :refer :all]))

(def input (first-line "2015/1_1.txt"))

(defn left-parens []
  (count-occurrences input \())

(defn right-parens []
  (count-occurrences input \)))

(defn one []
  (- (left-parens) (right-parens)))

(defn map-up-down [s]
  (map { \( 1 \) -1 } s))


(defn two []
  (count (take-while #(not= % -1) (reductions + 0 (map-up-down input)))))

