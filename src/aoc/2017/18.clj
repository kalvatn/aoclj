(ns aoc.2017.18
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]))

(def input (lines "2017/18.txt"))
; (def input [
;             "set a 1"
;             "add a 2"
;             "mul a a"
;             "mod a 5"
;             "snd a"
;             "set a 0"
;             "rcv a"
;             "jgz a -1"
;             "set a 1"
;             "jgz a -2"])

(defn parse-line [line]
  (s/split line #" "))

(defn parse-input [input]
  (map parse-line input))

(defn action-value [action]
  (read-string (nth action 2)))

(defn register-value [register x]
  (if (integer? (read-string x))
    (read-string x)
    (register x 0)))

(defn do-action [register action]
  (let [[op x y] [ (first action) (second action) (nth action 2)]
        xv (register-value register x)
        yv (register-value register y)
        ]
  (condp = op
    "set" (assoc register x yv)
    "add" (assoc register x (+ xv yv))
    "mul" (assoc register x (* xv yv))
    "mod" (assoc register x (mod xv yv)))))

(def actions (vec (parse-input input)))


(defn part-one [input]
  (loop [i 0
         register {}
         last-sound 0
         ]
    (if (or (neg? i) (> i (count actions))) register
      (let [action (actions i)
            [op x] [(first action) (second action)]
            xv (register-value register x)
            ]
          (println i action)
          (condp = op
            "jgz" (recur (int (+ i (if (pos? xv) (register-value register (nth action 2)) 1))) register last-sound)
            "snd" (recur
                    (inc i)
                    register
                    xv)
            "rcv" (if (pos? xv) last-sound (recur (inc i) register last-sound))
            (recur
              (inc i)
              (do-action register action)
              last-sound))))))

(defn part-two [input]
  )
