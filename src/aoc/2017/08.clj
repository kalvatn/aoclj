(ns aoc.2017.08
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]))

(def input (lines "2017/08.txt"))

(def r #"^(\w+) (inc|dec) (-?\d+) if (\w+) ([<>=!]+) (-?\d+)$")



(defn parse-line [line]
  (map read-string (rest (first (re-seq r line)))))

(defn parse-input [input]
  (vec (map parse-line input)))


(defn get-register-value [register k]
  (let [v (register (keyword k))]
    (if (nil? v) 0 v)))

(defn modify-value [op value change]
  (condp = op
    'inc (+ value change)
    'dec (- value change)))

(defn modify-register [register r1-k new-v check]
  (if (true? check) (assoc register r1-k new-v) register))

(defn part-one [input]
  (let [instructions (parse-input input)]
    (loop [register {}
           i 0
           ]
      (if (>= i (count instructions)) register
        (let [[r1 op change r2 c-op value2] (instructions i)
              r1-k (keyword r1)
              r2-k (keyword r2)
              r1-v (get-register-value register r1-k)
              r2-v (get-register-value register r2-k)
              new-v (modify-value op r1-v change)
              check (condp = c-op
                      '== (= r2-v value2)
                      (c-op r2-v value2))
              ]
          (println r1-k check c-op)
          (recur (modify-register register r1-k new-v check) (inc i)))))
      ))
(defn part-two [input]
  )
