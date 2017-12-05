(ns aoc.2017.05
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

; (def input (lines "2017/05_test.txt"))
(def input (lines "2017/05.txt"))
; (def input (lines "2017/05_frabe.txt"))

(defn parse-input [input]
  (vec (map read-string input)))

(defn v-fn-part-two ^long [^long v]
  (if (>= v 3)
    (dec v)
    (inc v)))

(defn count-steps [jumptable part-two?]
  (let [size (count jumptable)]
    (loop [vs (transient jumptable)
           i 0
           steps 0]
      (if (or (< i 0) (>= i size)) steps
        (let [^long v (vs i)]
          (recur
            (assoc! vs i (if part-two? (v-fn-part-two v) (inc v)))
            (+ v i)
            (inc steps)))))))


(defn part-one [input]
  (count-steps (parse-input input) false))

(defn part-two [input]
  (count-steps (parse-input input) true))
