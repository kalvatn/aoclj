(ns aoc.2015.01
  (:require [aoc.core.io :refer :all]))

(def input (first-line "2015/01.txt"))

(defn map-up-down [s]
  (map { \( 1 \) -1 } s))

(defn part-one [input]
  (reduce + (map-up-down input)))

(defn part-two [input]
  (count (take-while #(not= % -1) (reductions + 0 (map-up-down input)))))
