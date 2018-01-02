(ns aoc.2017.15
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]))

(def input (lines "2017/15.txt"))

(defn parse-line [line]
  (read-string (second (first (re-seq #"^Generator \w starts with (\d+)$" line)))))

(defn parse-input [input]
  (map parse-line input))

(def parsed (parse-input input))
(def A (first parsed))
(def B (second parsed))
; (def A 65)
; (def B 8921)
(def ^:const A-FAC 16807)
(def ^:const B-FAC 48271)
(def ^:const divisor 2147483647)

; https://stackoverflow.com/questions/12766590/get-n-least-significant-bits-from-an-int
(def ^:const l16 (- (bit-shift-left 1 16) 1))

(defn comp-l16 [a b] (= (bit-and a l16) (bit-and b l16)))

(defn calc [v fac]
  (rem (* v fac) divisor))

(defn calc-a-pt2 [prev]
  (loop [v prev]
    (let [nv (calc v A-FAC)]
      (if (= (mod nv 4) 0)
        nv
        (recur nv)))))

(defn calc-b-pt2 [prev]
  (loop [v prev]
    (let [nv (calc v B-FAC)]
      (if (= (mod nv 8) 0)
        nv
        (recur nv)))))



(defn part-one [input]
  (loop [a A
         b B
         n 1
         judge-count 0]
    (if (< 40000000 n) judge-count
      (let [na (calc a A-FAC)
            nb (calc b B-FAC)
            ]
      (recur na
             nb
             (inc n)
             (if (comp-l16 na nb) (inc judge-count) judge-count)
             )))))

(defn part-two [input]
  (loop [a A
         b B
         n 1
         judge-count 0]
    (if (< 5000000 n) judge-count
      (let [na (calc-a-pt2 a)
            nb (calc-b-pt2 b)]
      (recur na
             nb
             (inc n)
             (if (comp-l16 na nb) (inc judge-count) judge-count)
             )))))
