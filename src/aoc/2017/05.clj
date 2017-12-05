(ns aoc.2017.05
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

; (def input (lines "2017/05_test.txt"))
(def input (lines "2017/05.txt"))
; (def input (lines "2017/05_frabe.txt"))

(defn parse-input [input]
  (map read-string input))

; (defn count-steps [jumptable-vec v-fn] ; sending function as argument => 50ms slowdown
(defn count-steps [jumptable-vec part-two?]
  (let [jumptable (int-array jumptable-vec)
        size (alength jumptable)]
    (loop [vs jumptable
           i 0
           steps 0]
      (if (or (< i 0) (>= i size)) steps
      (let [v (aget vs i)
            ; v2 (int (v-fn v))]
            v2 (if (and part-two? (>= v 3)) (dec v) (inc v))]
        (recur (doto vs (aset i v2))
               (+ i v)
               (inc steps)))))))


(defn part-one [input]
  ; (time (count-steps (parse-input input) (fn ^long [^long v] (inc v)))))
  (time (count-steps (parse-input input) false)))

(defn part-two [input]
  ; (time (count-steps (parse-input input) (fn ^long [^long v] (if (>= v 3) (dec v) (inc v))))))
  (time (count-steps (parse-input input) true)))
