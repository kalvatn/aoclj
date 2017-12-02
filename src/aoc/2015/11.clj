(ns aoc.2015.11
  (:require [aoc.core.io :refer :all]
            [aoc.core.strings :refer :all]
            [aoc.core.ext :refer :all]))

(def input (first-line "2015/11.txt"))

(def letter-straights (set (map #(apply str %) (partition 3 1 (alphabet)))))

(defn increment-char [c]
  (if (= \z c) \a (char (inc (int c)))))

(defn increment-string [s]
  (loop [n (dec (count s)) r []]
    (if (neg? n) (apply str r)
      (let [c (increment-char (nth s n))]
        (if (not= \a c) (apply str (flatten [(take n s) c r]))
          (recur (dec n) (conj r c)))))))

(defn is-letter-straight? [s]
  (contains? letter-straights s))

(defn split-into-triples [s]
  (map #(apply str %) (partition 3 1 s)))

(defn has-letter-straight? [s]
  ; (any? true? (pmap #(.contains s %) letter-straights))
  (any? true? (map is-letter-straight? (split-into-triples s)))
)

(defn has-confusing? [s]
  (boolean (re-matches #".*[iol].*" s)))

(def not-confusing? (complement has-confusing?))

(defn has-double-letter-pair? [s]
  (>= (count (filter #(>= (count %) 2) (set (partition-by identity s)))) 2))

(defn is-valid? [s]
  (and (not-confusing? s) (has-double-letter-pair? s) (has-letter-straight? s)))

(defn part-one [input]
  ; (increment-string (last (take-while #(not (is-valid? %)) (iterate increment-string input)))))
  (first (filter is-valid? (iterate increment-string input)))
  )

(defn part-two [input]
  (part-one (increment-string "hepxxyzz")))
