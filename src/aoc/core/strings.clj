(ns aoc.core.strings
  (:require [aoc.core.macros :as macros]))

(macros/import-all-vars clojure.string)

(defn char-range [char-start char-end]
  (map char (range (int char-start) (inc (int char-end)))))

(defn char-range-plus [char-start number-of-chars]
  (char-range char-start (+ (int char-start) number-of-chars)))

(defn alphabet []
  (char-range \a \z))

; (defn split
;   ([s] (split s #" "))
;   ([s pattern] (clojure.string/split s pattern)))

(defn count-occurrences [s item]
  (get (frequencies s) item))

(defn generate-keywords [n]
  (map keyword (map str (char-range-plus \a (dec n)))))

(defn map-by-char [items]
  (zipmap (generate-keywords (count items)) items))

(defn rotate-left
  ([s] (rotate-left s 1))
  ([s n] (apply str (concat (drop n s) (take n s)))))

(defn rotations-left [s]
  (drop 1 (take (count s) (iterate rotate-left s))))

(defn rotate-right
  ([s] (rotate-right s 1))
  ([s n] (apply str (concat (take-last n s) (drop-last n s)))))

(defn rotations-right [s]
  (drop 1 (take (count s) (iterate rotate-right s))))

(defn padleft [s n pad]
  (apply str (concat (take (- n (count s)) (repeat pad)) s)))

(defn padright [s n pad]
  (apply str (concat s (take (- n (count s)) (repeat pad)))))


