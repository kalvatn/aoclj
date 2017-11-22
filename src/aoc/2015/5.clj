(ns aoc.2015.5
  (:require [aoc.core :refer :all]))

(def input (file-lines "2015/5.txt"))

(def disallowed [ "ab" "cd" "pq" "xy" ])

(def double-letters (map #(str % %) (alphabet)))

(defn has-three-vowels? [s]
  (<= 3 (count (re-seq #"[aeiou]" s))))


(defn has-double-letter? [s]
  (let [ h (map #(.contains s %) double-letters)]
  (any? true? h)))

(defn has-disallowed? [s]
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
    (not (nil? (some (fn [[x & xs]] (some #(> (- % x) 1) xs)) repeating-pair-indices)))))

(defn is-xyx? [s]
  (let [a (first s)
        b (second s)
        c (nth s 2)]
    (= true (= 3 (count s)) (= a c))))

(defn has-xyx? [s]
  (any? true? (map is-xyx? (partition 3 1 s))))

(defn is-nicer? [s]
  (not-any? false? [ (has-repeating-sequence? s) (has-xyx? s) ]))

(defn part-one
  ([] (part-one input))
  ([input] (let [answer (count (filter true? (map is-nice? input)))]
             (println "part one" answer))))

(defn part-two
  ([] (part-two input))
  ([input] (let [answer (count (filter true? (map is-nicer? input)))]
             (println "part two" answer))))

