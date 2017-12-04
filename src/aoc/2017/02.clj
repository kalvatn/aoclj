(ns aoc.2017.02
  (:require [aoc.core.io :refer :all]
            [aoc.core.math :as math]))

(def input (lines "2017/02.txt"))

(defn parse-input [input]
  (map #(read-string (str "[" % "]")) input))

(defn row-diff-largest-smallest [row]
  (- (reduce max row) (reduce min row)))

(defn divide [row]
  (/ (reduce max row) (reduce min row)))

(defn evenly-divisible? [[x y]]
  (integer? (/ x y)))

(defn max-min [row]
  [(reduce max row) (reduce min row)])

(defn row-evenly-divisible [row]
  (filter evenly-divisible? (map max-min (math/combinations-of-size row 2))))

(defn part-one [input]
  (reduce + (map row-diff-largest-smallest (parse-input input))))

(defn part-two [input]
  (reduce + (map divide (map first (map row-evenly-divisible (parse-input input))))))
