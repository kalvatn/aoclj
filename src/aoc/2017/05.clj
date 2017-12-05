(ns aoc.2017.05
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]))

(def input (lines "2017/05.txt"))
; (def input (lines "2017/05_test.txt"))

(defn parse-input [input]
  (reduce into (map-indexed (fn [k v] { k v }) (map read-string input))))

(defn jump [indexed-map v-fn]
  (loop [value-map indexed-map i 0 steps 0]
    (if (or (< i 0) (>= i (count value-map))) [ value-map steps ]
      (let [v (get value-map i)
            new-index (+ v i)
            new-v (v-fn v)]
        (recur (assoc value-map i new-v) new-index (inc steps))))))

(defn v-fn-part-two [v]
  (if (>= v 3)
    (dec v)
    (inc v)))

(defn part-one [input]
  (second (jump (parse-input input) inc)))

(defn part-two [input]
  (second (jump (parse-input input) v-fn-part-two)))
