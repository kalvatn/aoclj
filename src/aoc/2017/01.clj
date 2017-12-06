(ns aoc.2017.01
  (:require [aoc.core.io :refer :all]))

(def input (first-line "2017/01.txt"))
(set! *unchecked-math* :warn-on-boxed)

(defn parse-input [input]
  (map read-string (map str input)))

(defn filter-non-matching [grouped]
  (filter #(= (first %) (last %)) grouped))

(defn group-digits-part-one [digits]
  (filter-non-matching (partition 2 1 digits)))

(defn sum-digits-part-one [digits]
  (let [g (group-digits-part-one digits)
        first-equals-last (= (first digits) (last digits))]
      (+ ^long (reduce + (map first g)) (if first-equals-last ^long (first digits) 0))
    ))

(defn get-halfway-index [^long index ^long length]
  (mod (+ index ^long (/ length 2)) length))

(defn lookup-halfway [digits index]
  (nth digits (get-halfway-index index (count digits))))

(defn group-digits-part-two [digits]
  (filter-non-matching (map-indexed (fn [i v] [(lookup-halfway digits i) v]) digits)))

(defn sum-digits-part-two [digits]
  (reduce + (map first (group-digits-part-two digits))))

(defn part-one [input]
  (sum-digits-part-one (parse-input input)))
(defn part-two [input]
  (sum-digits-part-two (parse-input input)))
