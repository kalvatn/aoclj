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

(def != (complement =))


(defn run [input]
  (let [instructions (parse-input input)]
    (loop [register {}
           i 0
           mv (long 0)
           ]
      (if (>= i (count instructions)) [(reduce max (vals register)) mv]
        (let [[r1 op change r2 c-op value2] (instructions i)
              r1-k (keyword r1)
              r2-k (keyword r2)
              r1-v (get-register-value register r1-k)
              r2-v (get-register-value register r2-k)
              new-v (modify-value op r1-v change)
              check ((eval c-op) r2-v value2)
              new-max (reduce max (concat [mv] (vals register)))
              ]
          ; (println (instructions i))
          ; (println r1-k r2-v c-op value2 check register)
          (recur (modify-register register r1-k new-v check) (inc i) new-max))))
      ))
(defn part-one [input]
  (first (run input)))
(defn part-two [input]
  (second (run input)))
