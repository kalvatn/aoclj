(ns aoc.2015.6
  (:require [aoc.core.io :refer :all]
            [aoc.core.matrix :refer :all]))

(def input (lines "2015/6.txt"))

(def r #"^(turn (?:on|off)|toggle) (\d+),(\d+) through (\d+),(\d+)$")

(def TURN_ON "turn on")
(def TURN_OFF "turn off")
(def TOGGLE "toggle")

(defn part-one-action-fn [action]
  (condp = action
    TURN_OFF (fn [v] false)
    TURN_ON (fn [v] true)
    TOGGLE not))

(defn part-two-action-fn [action]
  (condp = action
    TURN_OFF (fn [v] (if (pos? v) (dec v) v))
    TURN_ON inc
    TOGGLE #(+ 2 %)))

(defn parse-v2 [s action-lookup-fn]
  (let [parsed (rest (first (re-seq r s)))]
    (conj (vec (map read-string (rest parsed))) (action-lookup-fn (first parsed)))))

(defn part-one
  ([input] (part-one input 1000))
  ([input grid-size]
   (let [matrix (vec-2d grid-size grid-size false)
         actions (map #(parse-v2 % part-one-action-fn) input)
         new-matrix (reduce assign-range-with-fn matrix actions)]
     ; (pprint new-matrix)
     (count (filter true? (reduce concat new-matrix))))))

(defn part-two
  ([input] (part-two input 1000))
  ([input grid-size]
   (let [matrix (vec-2d grid-size grid-size 0)
         actions (map #(parse-v2 % part-two-action-fn) input)
         new-matrix (reduce assign-range-with-fn matrix actions)]
     ; (pprint new-matrix)
     (reduce + (reduce concat new-matrix)))))

