(ns aoc.2017.03
  (:require [aoc.core.io :refer :all]
            [aoc.core.matrix :refer :all]
            [aoc.core.ext :refer :all]
            [aoc.core.math :refer :all]
            [clojure.math.numeric-tower :refer [sqrt ceil expt]]))

(def input (read-string (first-line "2017/03.txt")))

(defn switch-direction [dir]
  (condp = dir
    [0 1] [-1 0]
    [-1 0] [0 -1]
    [0 -1] [1 0]
    [1 0] [0 1]))

(defn next-pos [y x dir]
  (let [ny (+ y (first dir))
        nx (+ x (last dir))]
    [ny nx]
    ))

(defn next-turn [n]
  (ceil (/ (+ 3 (expt n 2)) 4)))

(defn gen [size]
  (let [turns (set (drop 2 (map next-turn (range (expt size 2)))))]
    (loop [y (int (/ size 2)) x y dir [0 1] r [] n 1]
      (if (= (dec n) (* size size)) r
        (let [
              should-turn (contains? turns n)
              new-dir (if should-turn (switch-direction dir) dir)
              [ny nx] (next-pos y x new-dir)
              ]
          (recur ny nx new-dir (conj r [y x n]) (inc n)))))))

(defn number-spiral [size]
  (let [matrix (vec-2d size size 0)
        spiral-seq (gen size)]
    (reduce assign matrix spiral-seq)
    ))


(defn part-one [input]
  (let [size (int (ceil (sqrt input)))
        spiral (number-spiral size)
        ]
    (first (filter #(not (nil? %))
       (for [y (range size) x (range size)] (if (= (lookup spiral y x) input) (abs (- y x)) nil))))))

(defn sum-neighbours [matrix y x]
  ; +' to fix integer overflow issue
  (reduce +' (map #(lookup matrix (first %) (second %)) (neighbours matrix [ y x ]))))

(defn sum-spiral [size matrix]
  (let [turns (set (drop 2 (map next-turn (range (expt size 2)))))]
    (loop [y (int (/ size 2)) x y dir [0 1] r [] n 1 m matrix]
      (if (= (dec n) (* size size)) m
        (let [
              should-turn (contains? turns n)
              new-dir (if should-turn (switch-direction dir) dir)
              [ny nx] (next-pos y x new-dir)
              tmp (sum-neighbours m y x)
              value (if (pos? tmp) tmp 1)
              ]
          (recur ny nx new-dir (conj r [y x value]) (inc n) (assign m [y x value])))))))

(defn part-two [input]
  (let [size (int (ceil (sqrt input)))
        matrix (vec-2d size size 0)
        spiral-seq (sum-spiral size matrix)]

    (first (sort (filter #(not (nil? %))
                   (for [y (range size) x (range size)]
                     (let [value (lookup spiral-seq y x)]
                       (if (> value input) value nil))))))
  ))
