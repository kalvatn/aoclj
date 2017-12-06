(ns aoc.2017.06
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]
            [aoc.core.string :as s]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

(def input (first-line "2017/06.txt"))

(defn parse-input [input]
  (vec (map read-string (s/split input #"[\t\s]"))))

(defn get-highest [banks]
  (first (sort-by (juxt #(* -1 ^int (val %)) key) (into {} (map-indexed vector banks)))))

(defn cycle-indices [drop-count take-count indices]
  (drop drop-count (take (+ ^int drop-count ^int take-count) (cycle indices))))

(defn index-increment-count [indices-to-increment bank-count]
  (let [default-inc-count (into {} (map #(vector % 0) (range 0 bank-count)))
        index-inc-count (into {} (map (juxt first #(count (second %))) (group-by identity indices-to-increment)))]
    (into (sorted-map) (merge default-inc-count index-inc-count))))

(defn redistribute [banks]
  (let [[^int h-index ^int h-value] (get-highest banks)
        bank-count (count banks)
        start-index (mod (inc h-index) bank-count)
        indices-to-increment (cycle-indices start-index h-value (range 0 bank-count))
        index-increment-counts (index-increment-count indices-to-increment bank-count)
        new-banks (vec (concat (take h-index banks) [0] (drop start-index banks)))
        ]
    (map #(+ ^int (new-banks (key %)) ^int (val %)) index-increment-counts)))

(defn cycle-redistribution [banks]
  (loop [banks banks
         steps 0
         seen {}]
    (if (contains? seen banks) [steps banks (- steps ^int (seen banks))]
      (recur (redistribute banks) (inc steps) (conj seen [banks steps])))))

(defn part-one [input]
  (first (cycle-redistribution (parse-input input))))

(defn part-two [input]
  ((cycle-redistribution (parse-input input)) 2))
