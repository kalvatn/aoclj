(ns aoc.2015.17
  (:require [aoc.core.io :refer :all]
            [aoc.core.math :refer :all]
            [aoc.core.strings :refer :all]))

(def input (lines "2015/17.txt"))


(defn parse-input [input]
  (map read-string input))

(def containers (map-by-char (parse-input input)))

(defn combinations-with-sum [items sum]
  (filter #(= (reduce + %) sum) (all-combinations items)))

(defn count-combinations [litres]
  (count (combinations-with-sum (keys containers) litres)))

(defn part-one [input]
  (println (parse-input input))
  )
(defn part-two [input]
  )
