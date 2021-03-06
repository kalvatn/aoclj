(ns aoc.2015.10
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]))

(def input (first-line "2015/10.txt"))

(defn look-and-say
  ([initial iterations] (nth (iterate look-and-say initial) iterations))
  ([initial] (s/join (mapcat (juxt count first) (partition-by identity initial)))))

(defn part-one [input]
  (count (look-and-say input 40)))

(defn part-two [input]
  (count (look-and-say input 50)))
