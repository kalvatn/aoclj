(ns aoc.2015.3
  (:require [aoc.core :refer :all]))

(def input (first-line "2015/3_1.txt"))

(def LEFT \<)
(def RIGHT \>)
(def UP \^)
(def DOWN \v)

(defn parse-input [input]
  (map { LEFT [-1 0] RIGHT [1 0] UP [0 1] DOWN [0 -1] } input))

(defn visited [input]
  (set (reductions #(map + %1 %2) [0 0] (parse-input input))))

(defn count-visited [input]
  (count (visited input)))

(defn part-one
  ([] (part-one input))
  ([input] (let [ answer (count (visited input))]
                                (println "part one" answer)
                                answer)))

(defn part-two
  ([] (part-two input))
  ([input] (let [data (parse-input input)
                 p (partition 2 input)
                 answer (count (set (mapcat visited [(map first p) (map second p)])))]
             (println "part two" answer)
             answer)))
