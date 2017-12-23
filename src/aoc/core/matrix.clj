(ns aoc.core.matrix
  (:require [aoc.core.string :as s]))

(set! *unchecked-math* :warn-on-boxed)

(defn pprint-matrix
  ([matrix] (pprint-matrix matrix (fn [v] v)))
  ([matrix value-format-fn]
  (println
    (s/join
      (reduce concat
              (interpose "\n"
                         (map #(map value-format-fn %) matrix)))))))

(defn pprint-number-matrix
  ([matrix] (pprint-number-matrix matrix 1))
  ([matrix digit-size]
   (pprint-matrix matrix #(format (str "%0" digit-size "d ") %))))

(defn range-pairs [^long y1 ^long x1 ^long y2 ^long x2]
  (for [y (range y1 (inc y2)) x (range x1 (inc x2))] [y x]))

(defn create-2d [rows cols initial-value]
  (vec (take rows (repeat (vec (take cols (repeat initial-value)))))))

(defn row [matrix y]
  (nth matrix y))

(defn lookup [matrix y x]
  (nth (row matrix y) x))

(defn assign [matrix [y x value]]
  (assoc matrix y (assoc (nth matrix y) x value)))

(defn assign-range [ matrix [ y1 x1 y2 x2 value] ]
  (reduce assign matrix (map #(conj % value) (range-pairs y1 x1 y2 x2))))

(defn assign-with-fn [matrix [y x value-fn]]
  (assign matrix [y x (value-fn (lookup matrix y x))]))

(defn assign-range-with-fn [ matrix [ y1 x1 y2 x2 value-fn] ]
  (reduce assign-with-fn matrix (map #(conj % value-fn) (range-pairs y1 x1 y2 x2))))

(defn out-of-bounds? [ matrix [^long y ^long x] ]
  (let [size (count matrix)]
    (or (>= x size) (< x 0) (>= y size) (< y 0))))

(defn neighbours [matrix [^long y ^long x]]
  (filter #(not (out-of-bounds? matrix %))
            [[y (inc x)]
             [y (dec x)]
             [(dec y) x]
             [(inc y) x]
             [(inc y) (inc x)]
             [(inc y) (dec x)]
             [(dec y) (inc x)]
             [(dec y) (dec x)]]))

(defn neighbours-4 [matrix [^long y ^long x]]
  (filter #(not (out-of-bounds? matrix %))
            [[y (inc x)]
             [y (dec x)]
             [(dec y) x]
             [(inc y) x]]))
