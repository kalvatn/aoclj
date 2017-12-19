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

(defn knot [coll current-index size]
  (let [pre-rot (rotate coll current-index)
        new-seq (concat (reverse-range pre-rot 0 size) (drop size pre-rot))
        post-rot (rotate new-seq (- (count coll) current-index))]
    post-rot))

(defn knot-hash [coll lengths]
  (let [list-size (count coll)]
    (loop [coll coll
           i 0
           pos 0
           step 0]
      (if (>= i (count lengths)) coll
        (let [size (lengths i)]
          (recur (knot coll pos size)
                 (inc i)
                 (int (mod (+ pos size step) list-size))
                 (inc step)))))))

(defn xor-coll [coll]
  (reduce bit-xor coll))

(defn xor-all [coll]
  (map #(reduce bit-xor %) (partition 16 coll)))

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
   (let [initial (range 0 256)
         ; lengths [17 31 73 47 23]
         lengths (concat (map int input) [17 31 73 47 23])
         lengths-repeated (vec (flatten (repeat 64 lengths)))
         knotted (knot-hash initial lengths-repeated)
         xored (xor-all knotted)
         hexed (map #(format "%02x" %) xored)
         result (clojure.string/join hexed)
         ]
     result
     )
   )

