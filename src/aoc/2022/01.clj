(ns aoc.2022.01
  (:require [aoc.core.io :refer :all]))

(def input (slurp "resources/2022/01.txt"))

(defn sum-and-sort-groups [input]
  (as-> input v
    (clojure.string/split v #"\n\n")
    (map #(clojure.string/split-lines %) v)
    (map #(map read-string %) v)
    (map #(reduce + %) v)
    (sort v)))


(defn part-one [input]
  (first (take-last 1 (sum-and-sort-groups input))))

(defn part-two [input]
  (reduce + (take-last 3 (sum-and-sort-groups input))))
