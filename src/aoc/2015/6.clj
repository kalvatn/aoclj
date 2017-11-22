(ns aoc.2015.6
  (:require [aoc.core :refer :all]))

(def input (file-lines "2015/6.txt"))

(def r #"^(turn (?:on|off)|toggle) (\d+),(\d+) through (\d+),(\d+)$")

(def TURN_ON "turn on")
(def TURN_OFF "turn off")
(def TOGGLE "toggle")

(def OFF 0)
(def ON 1)

(defn parse-instruction [s]
  (let [parsed (rest (first (re-seq r s)))]
    (conj (map read-string (rest parsed)) (first parsed))))

(defn create-matrix [size]
  (vec (take size (repeat (vec (take size (repeat OFF)))))))

(defn state [grid y x]
  ; (get-in grid [y x]))
  (nth (nth grid y) x))

(defn grid-action [ grid [ action y x] ]
  (let [current (state grid y x)]
  (condp = action
    TURN_ON (assoc grid y (assoc (nth grid y) x ON))
    TURN_OFF (assoc grid y (assoc (nth grid y) x OFF))
    TOGGLE (assoc grid y (assoc (nth grid y) x (if (= ON current) OFF ON))))))

(defn grid-action-part-two [ grid [ action y x] ]
  (let [current (state grid y x)]
  (condp = action
    TURN_ON (assoc grid y (assoc (nth grid y) x (inc current)))
    TURN_OFF (assoc grid y (assoc (nth grid y) x (if (pos? current) (dec current) current)))
    TOGGLE (assoc grid y (assoc (nth grid y) x (+ current 2))))))

(defn create-range-actions [ action y1 x1 y2 x2 ]
  ; (println action y1 x1 y2 x2)
  (for [y (range y1 (inc y2)) x (range x1 (inc x2))]
    [action y x]))

(defn pprint-grid [grid]
  (println (for [r grid] (str r "\n"))))

(defn range-action [ grid action y1 x1 y2 x2 ]
  (reduce grid-action grid (create-range-actions action y1 x1 y2 x2)))

(defn range-action-part-two [ grid action y1 x1 y2 x2 ]
  (reduce grid-action-part-two grid (create-range-actions action y1 x1 y2 x2)))

(defn parse-multiple-instruction [lines]
  (map parse-instruction lines))

(defn count-on [grid]
  (count (filter #(= 1 %) (reduce concat grid))))

(defn sum-brightness [grid]
  (reduce + (reduce concat grid)))

(defn part-one
  ([] (part-one input 1000))
  ([input grid-size] (let [ original-grid (create-matrix grid-size)
                           actions (map parse-instruction input)
                           new-grid (reduce (fn [grid [ action y1 x1 y2 x2 ]] (range-action grid action y1 x1 y2 x2)) original-grid actions)]
                       (println "part one" (count-on new-grid))
                       new-grid)))

(defn part-two
  ([] (part-two input 1000))
  ([input grid-size] (let [ original-grid (create-matrix grid-size)
                           actions (map parse-instruction input)
                           new-grid (reduce (fn [grid [ action y1 x1 y2 x2 ]] (range-action-part-two grid action y1 x1 y2 x2)) original-grid actions)]
                       (println "part two" (sum-brightness new-grid))
                       new-grid)))

