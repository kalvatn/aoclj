(ns aoc.2016.01
  (:require [aoc.core.io :refer :all]
            [aoc.core.math :as math]))

(def input (first-line "2016/01.txt"))

(defn parse-input [input]
  (map (fn [[d n]] [(keyword d) (read-string n)]) (map rest (re-seq #"(R|L)(\d+)" input))))

(defn next-facing [current-facing direction]
  (condp = current-facing
    :N (if (= :R direction) :E :W)
    :E (if (= :R direction) :S :N)
    :S (if (= :R direction) :W :E)
    :W (if (= :R direction) :N :S)))

(defn next-pos [pos [direction steps]]
  (condp = (next-facing (last pos) direction)
    :N [ (+ (first pos) steps) (second pos) :N]
    :S [ (- (first pos) steps) (second pos) :S]
    :E [ (first pos) (+ (second pos) steps) :E]
    :W [ (first pos) (- (second pos) steps) :W]))

(defn get-distance [pos]
  (+ (math/abs (first pos)) (math/abs (second pos))))


(defn part-one [input]
  (get-distance (reduce next-pos [0 0 :N] (parse-input input)))
  )
(defn part-two [input]
  )
