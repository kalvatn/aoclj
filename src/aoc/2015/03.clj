(ns aoc.2015.03
  (:require [aoc.core.io :refer :all]))

(def input (first-line "2015/03.txt"))

(def LEFT \<)
(def RIGHT \>)
(def UP \^)
(def DOWN \v)

(defn parse-input [input]
  (map { LEFT [-1 0] RIGHT [1 0] UP [0 1] DOWN [0 -1] } input))

(defn visited [input]
  (set (reductions #(map + %1 %2) [0 0] (parse-input input))))

(defn part-one [input]
  (count (visited input)))

(defn part-two [input]
  (let [data (parse-input input)
        p (partition 2 input)
        answer (count (set (mapcat visited [(map first p) (map second p)])))]
    answer))
