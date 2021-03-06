(ns aoc.2015.05
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]
            [aoc.core.string :as s]))

(def input (lines "2015/05.txt"))

(set! *unchecked-math* :warn-on-boxed)

(def disallowed [ "ab" "cd" "pq" "xy" ])

(def double-letters (map #(str % %) (s/alphabet)))

(defn has-three-vowels? [s]
  (<= 3 (count (re-seq #"[aeiou]" s))))


(defn has-double-letter? [^String s]
  (let [ h (map #(.contains s %) double-letters)]
  (any? true? h)))

(defn has-disallowed? [^String s]
  (let [ h (map #(.contains s %) disallowed)]
  (any? true? h)))

(defn is-nice? [s]
  (not-any? false? [ (has-three-vowels? s) (has-double-letter? s) (not (has-disallowed? s))]))

(defn group-by-pair [s]
  (apply merge-with concat (map-indexed (fn [a b] { b [a] }) (partition 2 1 s))))

(defn has-repeating-sequence? [s]
  (let [grouped (group-by-pair s)
        repeating-pair-indices (filter #(> (count %) 1) (vals grouped))
        ]
    (not (nil? (some (fn [[^long x & xs]] (some #(> ^long (- ^long % x) 1) xs)) repeating-pair-indices)))))

(defn is-xyx? [s]
  (let [a (first s)
        b (second s)
        c (nth s 2)]
    (= true (= 3 (count s)) (= a c))))

(defn has-xyx? [s]
  (any? true? (map is-xyx? (partition 3 1 s))))

(defn is-nicer? [s]
  (not-any? false? [ (has-repeating-sequence? s) (has-xyx? s) ]))

(defn part-one [input]
  (count (filter true? (map is-nice? input))))

(defn part-two [input]
  (count (filter true? (map is-nicer? input))))

