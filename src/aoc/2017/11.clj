(ns aoc.2017.11
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]
            [aoc.core.math :as m]))

(def input (first-line "2017/11.txt"))

(defn parse-input [input]
  (map keyword (s/split input #",")))

(defrecord Cube [x y z])

(def start (Cube. 0 0 0))

(defn move [current direction]
  (condp = direction
    :n  (Cube. (:x current) (inc (:y current)) (dec (:z current)))
    :s  (Cube. (:x current) (dec (:y current)) (inc (:z current)))

    :ne (Cube. (inc (:x current)) (:y current) (dec (:z current)))
    :sw (Cube. (dec (:x current)) (:y current) (inc (:z current)))

    :nw (Cube. (dec (:x current)) (inc (:y current)) (:z current))
    :se (Cube. (inc (:x current)) (dec (:y current)) (:z current))
    ))

(defn distance [a b]
  (/ (+ (m/abs (- (:x a) (:x b)))
        (m/abs (- (:y a) (:y b)))
        (m/abs (- (:z a) (:z b)))) 2))

(defn part-one [input]
  (distance start (reduce move start (parse-input input)))
  )
(defn part-two [input]
  (reduce max (map (partial distance start) (reductions move start (parse-input input))))
  )
