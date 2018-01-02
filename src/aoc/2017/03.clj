(ns aoc.2017.03
  (:require [aoc.core.io :refer :all]
            [aoc.core.matrix :as matrix]
            [aoc.core.ext :refer :all]
            [aoc.core.math :as math]))

(def input (read-string (first-line "2017/03.txt")))

(set! *unchecked-math* :warn-on-boxed)

(defn switch-direction [dir]
  (condp = dir
    [0 1] [-1 0]
    [-1 0] [0 -1]
    [0 -1] [1 0]
    [1 0] [0 1]))

(defn next-pos [^long y ^long x dir]
  (let [ny (+ y ^long (first dir))
        nx (+ x ^long (last dir))]
    [ny nx]
    ))

(defn next-turn [n]
  (math/ceil (/ ^long (+' 3 ^long (math/expt n 2)) 4)))

(defn gen [size]
  (let [turns (set (drop 2 (map next-turn (range (math/expt size 2)))))]
    (loop [y (int (/ ^int size 2)) x y dir [0 1] r [] n 1]
      (if (= (dec n) (* ^int size ^int size)) r
        (let [
              should-turn (contains? turns n)
              new-dir (if should-turn (switch-direction dir) dir)
              [^int ny ^int nx] (next-pos y x new-dir)
              ]
          (recur (int ny) (int nx) new-dir (conj r [y x n]) (inc n)))))))

(defn number-spiral [size]
  (let [matrix (matrix/create-2d size size 0)
        spiral-seq (gen size)]
    (reduce matrix/assign matrix spiral-seq)
    ))


(defn part-one [input]
  (let [size (int (math/ceil (math/sqrt input)))
        spiral (number-spiral size)
        ]
    (first (remove nil?
       (for [^int y (range size) ^int x (range size)]
         (when (= (matrix/lookup spiral [y x]) input) (math/abs (- y x))))))))

(defn sum-neighbours [matrix y x]
  ; +' to fix integer overflow issue (autopromotes longs)
  (reduce +' (map #(matrix/lookup matrix %) (matrix/neighbours matrix [ y x ]))))

(defn sum-spiral [size matrix]
  (let [turns (set (drop 2 (map next-turn (range (math/expt size 2)))))
        start-point (int (/ ^int size 2))
        ]
    (loop [y start-point
           x start-point
           dir [0 1]
           r []
           n (int 1)
           m matrix]
      (if (= (dec n) (* ^int size ^int size)) m
        (let [
              should-turn (contains? turns n)
              new-dir (if should-turn (switch-direction dir) dir)
              [^int ny ^int nx] (next-pos y x new-dir)
              ^int tmp (sum-neighbours m y x)
              ^int value (if (pos? tmp) tmp 1)
              ]
          (recur (int ny) (int nx) new-dir (conj r [y x value]) (inc n) (matrix/assign m [y x value])))))))

(defn part-two [input]
  (let [size (int (math/ceil (math/sqrt input)))
        matrix (matrix/create-2d size size 0)
        spiral-seq (sum-spiral size matrix)]

    (first (sort (remove nil?
                   (for [y (range size) x (range size)]
                     (let [^int value (matrix/lookup spiral-seq [y x])]
                       (when (> value ^int input) value))))))
  ))
