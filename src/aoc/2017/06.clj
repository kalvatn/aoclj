(ns aoc.2017.06
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]
            [aoc.core.string :as s]))

(def input (first-line "2017/06.txt"))

(defn parse-input [input]
  (vec (map read-string (s/split input #"[\t\s]"))))

(defn get-highest [banks]
  (first (sort-by (juxt #(* -1 (val %)) key) (into {} (map-indexed vector banks)))))

(defn cycle-indices [drop-count take-count indices]
  (drop drop-count (take (+ drop-count take-count) (cycle indices))))

(defn index-increment-count [indices-to-increment bank-count]
  (let [default-inc-count (into {} (map #(vector % 0) (range 0 bank-count)))
        index-inc-count (into {} (map (juxt first #(count (second %))) (group-by identity indices-to-increment)))]
    (into (sorted-map) (merge default-inc-count index-inc-count))))

(defn redistribute [banks]
  (let [[h-index h-value] (get-highest banks)
        start-index (mod (inc h-index) (count banks))
        indices-to-increment (cycle-indices start-index h-value (range 0 (count banks)))
        index-increment-counts (index-increment-count indices-to-increment (count banks))
        new-banks (vec (concat (take h-index banks) [0] (drop start-index banks)))
        ]
    (map #(+ (new-banks (key %)) (val %)) index-increment-counts)))

(defn cycle-redistribution [banks]
  (loop [banks banks
         steps 0
         seen {}]
    (if (contains? seen banks) [steps banks (- steps (seen banks))]
      (recur (redistribute banks) (inc steps) (conj seen [banks steps])))))

(defn part-one [input]
  (first (cycle-redistribution (parse-input input))))

(defn part-two [input]
  ((cycle-redistribution (parse-input input)) 2))
