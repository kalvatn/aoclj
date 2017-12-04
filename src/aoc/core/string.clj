(ns aoc.core.string
  (:require [aoc.core.macros :as macros]
            [potemkin]))

(macros/import-all-vars-with-exclusions clojure.string [reverse replace])

(potemkin/import-fn clojure.string/replace str-replace)
(potemkin/import-fn clojure.string/reverse str-reverse)

(defn char-range [char-start char-end]
  (map char (range (int char-start) (inc (int char-end)))))

(defn char-range-plus [char-start number-of-chars]
  (char-range char-start (+ (int char-start) number-of-chars)))

(defn alphabet []
  (char-range \a \z))

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

(defn replace-index [s index replacement]
  (str (subs s 0 index) replacement (subs s (inc index) (count s))))

(defn replace-range [s start-index end-index replacement]
  (reduce #(replace-index %1 (first %2) (second %2)) s (map vector (range start-index (inc end-index)) (repeat replacement))))

(defn swap-index [s index1 index2]
  (let [c1 (nth s index1)
        c2 (nth s index2)]
    (reduce #(replace-index %1 (first %2) (second %2)) s [[index1 c2] [index2 c1]])))

(defn reverse-range [s start end]
  (str (subs s 0 start)
       (str-reverse (subs s start (inc end)))
       (subs s (inc end) (count s))))

(defn move-element [v from to]
  (if (> to from)
    (concat
      (subvec v 0 from)
      (subvec v (inc from) (inc to))
      [(nth v from)]
      (subvec v (inc to) (count v)))
    (concat
      (subvec v 0 to)
      [(nth v from)]
      (subvec v to from)
      (subvec v (inc from) (count v)))))

(defn move-index [s from-index to-index]
  (apply str (move-element (vec (seq s)) from-index to-index)))










