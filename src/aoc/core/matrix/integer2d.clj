(ns aoc.core.matrix.integer2d
  (:require [aoc.core.matrix :refer [range-pairs]]))

(defn create-with-value-fn [rows cols value-fn]
  (into-array
    (map int-array
         (take rows
               (partition cols (value-fn))))))

(defn create
  ([rows cols] (create rows cols 0))
  ([rows cols initial-value] (create-with-value-fn rows cols #(repeat initial-value))))

(defn row [matrix y]
  (aget matrix y))

(defn lookup [matrix ^long y ^long x]
  (aget matrix y x))

(defn assign [matrix [y x value]]
  (doto matrix (aset-int y x value)))

(defn assign-range [ matrix [ y1 x1 y2 x2 value] ]
  (reduce assign matrix (map #(conj % value) (range-pairs y1 x1 y2 x2))))

(defn assign-with-fn [matrix [y x value-fn]]
  (assign matrix [y x (value-fn (lookup matrix y x))]))

(defn assign-range-with-fn [ matrix [ y1 x1 y2 x2 value-fn] ]
  (reduce assign-with-fn matrix (map #(conj % value-fn) (range-pairs y1 x1 y2 x2))))
