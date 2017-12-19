(ns aoc.2017.10
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]))

(def input (first-line "2017/10.txt"))

(defn parse-input [line]
  (vec (map read-string (s/split line #","))))


(defn select-range [coll current-index size]
  (take size (drop current-index (cycle coll))))

(defn reverse-range [coll current-index size]
  (reverse (select-range coll current-index size)))

(defn rotate [coll n]
  (concat (drop n coll) (take n coll)))

(defn knot [coll current-index size step-size]
  (let [rot (rotate coll current-index)
        pre (concat (reverse-range rot 0 size) (drop size rot))
        fin (rotate pre (- (count coll) current-index))]
    fin))

(defn knot-hash [coll lengths]
  (let [list-size (count coll)]
    (loop [coll coll
           i 0
           pos 0
           step 0]
      (println coll i pos step)
      (if (>= i (count lengths)) coll
        (let [size (lengths i)]
          (recur (knot coll pos size step)
                 (inc i)
                 (mod (+ pos size step) list-size)
                 (inc step)))))))


(defn part-one
  ([input] (part-one input 256))
  ([input list-size]
   (let [initial (range 0 list-size)
         lengths (parse-input input)
         knotted (knot-hash initial lengths)
         ]
     (* (first knotted) (second knotted))
     )
   ))
(defn part-two [input]
  )
