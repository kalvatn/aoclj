(ns aoc.2017.04
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]
            [aoc.core.math :as math]))

(def input (lines "2017/04.txt"))

(defn parse-line [line]
  (clojure.string/split line #"\s"))


(defn no-duplicate? [words]
  (= (count words) (count (set words))))

(defn sort-word [word]
  (sort (seq word)))

(defn is-anagram? [[w1 w2]]
  (= (sort-word w1) (sort-word w2)))

(defn has-anagram? [words]
  (let [c (math/combinations words 2)]
    (any? true? (map is-anagram? c))))

(defn filter-duplicates [input]
  (->> input
      (map parse-line)
      (filter no-duplicate?)
  ))

(defn part-one [input]
  (count (filter-duplicates input))
  )


(defn part-two [input]
  (let [p1 (filter-duplicates input)]
    (count (remove has-anagram? p1))
  ))
