(ns aoc.2015.08
  (:require [aoc.core.io :refer :all]))

(def input (lines "2015/08.txt"))

(set! *unchecked-math* :warn-on-boxed)

(defn count-string-chars ^long [s]
  (count (re-seq #"[\w]|\\\"|\\x[0-9a-f]{2}|\\\\" s)))

(defn escape [s]
  (-> s
      (clojure.string/replace "\\" "\\\\")
      (clojure.string/replace "\"" "\\\"")))

(defn count-escaped ^long [s]
  (+ (count (escape s)) 2))

(defn part-one [input]
  (reduce + (map #(- (count %) (count-string-chars %)) input)))

(defn part-two [input]
  (reduce + (map #(- (count-escaped %) (count %)) input)))
