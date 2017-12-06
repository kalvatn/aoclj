(ns aoc.2016.01
  (:require [aoc.core.io :refer :all]
            [aoc.core.math :as math]))

(def input (first-line "2016/01.txt"))

(set! *unchecked-math* :warn-on-boxed)

(defn parse-input [input]
  (map (fn [[d n]] [(keyword d) (read-string n)]) (map rest (re-seq #"(R|L)(\d+)" input))))

(defn next-facing [current-facing direction]
  (condp = current-facing
    :N (if (= :R direction) :E :W)
    :E (if (= :R direction) :S :N)
    :S (if (= :R direction) :W :E)
    :W (if (= :R direction) :N :S)))

(defn next-pos [pos [direction ^long steps]]
  (let [[^long y ^long x facing] pos]
  (condp = (next-facing facing direction)
    :N [ (+ y steps) x :N]
    :S [ (- y steps) x :S]
    :E [ y (+ x steps) :E]
    :W [ y (- x steps) :W])))

(defn get-distance [pos]
  (+ ^long (math/abs (first pos)) ^long (math/abs (second pos))))

(defn part-one [input]
  (get-distance (reduce next-pos [0 0 :N] (parse-input input)))
  )
(defn part-two [input]
  )
