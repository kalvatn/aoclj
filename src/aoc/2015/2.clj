(ns aoc.2015.2
  (:require [aoc.core :refer :all]))

(def input (file-lines "2015/2_1.txt"))


(defn parse-line [line]
  (map read-string (split line #"x")))

(defn calc-box-paper [dimensions]
  (let [l (first dimensions)
        w (second dimensions)
        h (nth dimensions 2)
        sides [(* l w) (* w h) (* h l)]
        smallest (apply min sides)
        surface (reduce + (map #(* 2 %) sides))
        ]
        ; (println l w h)
        ; (println sides)
        ; (println smallest)
        ; (println surface)

        (+ surface smallest)))

(defn calc-box-ribbon [dimensions]
  (let [l (first dimensions)
        w (second dimensions)
        h (nth dimensions 2)
        smallest-sides (take 2 (sort < [l w h]))
        smallest-perimiter (reduce + (map #(* 2 %) smallest-sides))
        volume (reduce * [l w h])
        ]
    ; (println l w h)
    ; (println smallest-perimiter)
    ; (println volume)
    (+ volume smallest-perimiter)))

(defn one []
  (let [dimensions (map parse-line input)
        paper-per-box (map calc-box-paper dimensions)
        total (reduce + paper-per-box)]
    (println total)
    total
    ))

(defn two []
  (let [dimensions (map parse-line input)
        ribbon-per-box (map calc-box-ribbon dimensions)
        total (reduce + ribbon-per-box)]
    (println total)
    total))

