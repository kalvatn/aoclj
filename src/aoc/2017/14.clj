(ns aoc.2017.14
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]
            [aoc.core.matrix :as matrix]
            [aoc.2017.10 :as day10]))

(def input (first-line "2017/14.txt"))
; (def input "flqrgnkx")

(defn knot-hashes [key-string]
  (let [filename (str "/tmp/aoc.2017.14." key-string ".knot-hashes")
        ^java.io.File knot-hash-file (clojure.java.io/as-file filename)]
    (if (.exists knot-hash-file)
      (s/split (slurp filename) #"\n")
      (let [hashes (map day10/part-two (for [x (range 0 128)] (str key-string "-" x)))]
        (spit filename (s/join "\n" hashes))
        hashes))))

(defn hexdigit-to-binary [hex]
  (format "%04d" (read-string (Integer/toBinaryString (read-string (str "0x" hex))))))

(defn knot-hash-to-binary [knot-hash]
  (map hexdigit-to-binary knot-hash))

(defn knot-bits [knot-hashes]
  (map s/join (map knot-hash-to-binary knot-hashes)))

(defn bits-to-grid [bits]
  (->> (map #(map (fn [v] (condp = v
                            \1 "#"
                            \0 ".")) %) bits)
       (map vec)
       vec))

(defn find-next-unmarked-coord [grid]
  (loop [y 0
         x 0]
    (if (= "#" (matrix/lookup grid [y x]))
      [y x]
      (let [nx (if (= (inc x) (count grid)) 0 (inc x))
            ny (if (= (inc x) (count grid)) (inc y) y)]
        (if (= ny (count grid)) [y x] (recur ny nx))))))

(defn unmarked-neighbours [grid pos]
  (let [n4 (matrix/neighbours-4 grid pos)]
    (remove nil? (for [p n4]
                   (when (= (matrix/lookup grid p) "#") p)))))

(defn mark-region [grid posn]
  (let [[y x n] posn
        v (matrix/lookup grid [y x])]
    (if (not= "#" v) grid
      (let [marked (matrix/assign grid [y x n])
            n4 (map #(conj % n) (unmarked-neighbours marked [y x]))]
        (if (empty? n4)
          marked
          (reduce mark-region marked n4))))))

(defn mark-regions [grid]
  (loop [g grid
         pos [0 0]
         n 1]
    (let [marked (mark-region g (vec (conj pos n)))
          next-pos (find-next-unmarked-coord marked)]
      (if (= next-pos [(dec (count g)) (dec (count g))]) [ marked n]
        (recur marked next-pos (inc n))))))

(defn pprint-grid [grid]
  (matrix/pprint-matrix grid))

(defn part-one [input]
  (->> (knot-hashes input)
       knot-bits
       (apply concat)
       (remove #(= \0 %))
       count)
  )

(defn part-two [input]
  (->> (knot-hashes input)
       knot-bits
       bits-to-grid
       mark-regions
       second
       ))
