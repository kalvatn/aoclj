(ns aoc.2015.06
  (:require [aoc.core.io :refer :all]
            [aoc.core.matrix :as matrix]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

(def input (lines "2015/06.txt"))

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
    TURN_OFF (fn [^long v] (if (pos? v) (dec v) v))
    TURN_ON (fn [^long v] (inc v))
    TOGGLE (fn [^long v] (+ 2 v))))

(defn parse-v2 [s action-lookup-fn]
  (let [parsed (rest (first (re-seq r s)))]
    (conj (vec (map read-string (rest parsed))) (action-lookup-fn (first parsed)))))

(defn part-one
  ([input] (part-one input 1000))
  ([input grid-size]
   (let [matrix (matrix/create-2d grid-size grid-size false)
         actions (map #(parse-v2 % part-one-action-fn) input)
         new-matrix (reduce matrix/assign-range-with-fn matrix actions)]
     (count (filter true? (flatten new-matrix))))))

(defn part-two
  ([input] (part-two input 1000))
  ([input grid-size]
   (let [matrix (matrix/create-2d grid-size grid-size 0)
         actions (map #(parse-v2 % part-two-action-fn) input)
         new-matrix (reduce matrix/assign-range-with-fn matrix actions)]
     (reduce + (flatten new-matrix)))))
