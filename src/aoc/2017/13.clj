(ns aoc.2017.13
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]))

(def input (lines "2017/13.txt"))
; (def input (lines "2017/13_test.txt"))

(defn parse-line [line]
  (vec (map read-string (s/split line #":"))))

(defn parse-input [input]
  (into {} (map parse-line input)))

(def layers (parse-input input))

(def total-layers (reduce max (map first layers)))

(defrecord Scanner [depth pos ascending])

(defn bounce-range [n m ascending?]
  (if ascending?
    (if (= m n) [(dec n) false] [(inc n) true])
    (if (= 0 n) [(inc n) true] [(dec n) false])))

(defn move-scanner [scanner]
  (let [depth (:depth scanner)
        pos (:pos scanner)
        ascending? (:ascending scanner)
        range (layers depth)
        [new-pos new-dir] (bounce-range pos (dec range) ascending?)]
    (Scanner. depth new-pos new-dir)))

(defn move-scanners [scanners]
  (into {} (map #(vec [(first %) (move-scanner (second %))]) scanners)))

(def scanners (into {} (map #(vec [(first %) (Scanner. (first %) 0 true)]) layers)))

(defn part-one [input]
  (loop [depth 0
         scanners scanners
         trip-severity 0]
    (let [ caught? (= (:pos (scanners depth)) 0)]
      (if (> depth total-layers) trip-severity
          (recur (inc depth)
                 (move-scanners scanners)
                 (if caught? (+ trip-severity (* (layers depth) depth)) trip-severity)
                 )))))

(defn scanner-pos [depth, picosecond]
  (let [offset (mod picosecond (* 2 (dec depth)))]
    (if (> offset (dec depth))
      (* 2 (dec depth))
      offset)))

(defn part-two [input]
  (loop [n 0]
    (let [scan (map (fn [[k v]] (scanner-pos v (+ k n))) layers)]
      (if (not-any? zero? scan) n (recur (inc n))))))
