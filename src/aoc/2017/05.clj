(ns aoc.2017.05
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

; (def input (lines "2017/05_test.txt"))
(def input (lines "2017/05.txt"))

(defn parse-input [input]
  (transient (reduce into (map-indexed (fn [k v] { k v }) (map read-string input)))))
(defn parse-input2 [input]
  (vec (map read-string input)))

(defn jump [indexed-map v-fn]
  (loop [value-map indexed-map i 0 steps 0]
    (if (or (< i 0) (>= i (count value-map))) [ value-map steps ]
      (let [v (get value-map i)
            ^long new-index (+ v i)
            ^long new-v (v-fn v)]
        (recur (assoc! value-map i new-v) new-index (inc steps))))))

(defn v-fn-part-two ^long [^long v]
  (if (>= v 3)
    (dec v)
    (inc v)))

(defn part-one [input]
  (second (jump (parse-input input) inc)))

(defn part-two [input]
  (let [initial-v (parse-input2 input)
        size (count initial-v)]
    (loop [v (transient initial-v) i (int 0) steps (int 0) ]
      (if (and (>= i 0) (< i size))
        (let [value (int (nth v i))] (recur (assoc! v i (int (v-fn-part-two value))) (int (+ i value)) (inc steps)))
        steps))))

