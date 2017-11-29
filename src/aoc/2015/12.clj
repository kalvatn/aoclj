(ns aoc.2015.12
  (:require [aoc.core.io :refer :all]
            [clojure.walk :refer [prewalk]]))

(def input (first-line "2015/12.txt"))

(defn extract-numbers [s]
  (map read-string (re-seq #"\-?[0-9]+" s)))

(defn has-red? [data]
  (some #((set %) "red") data))

(defn extract-numbers-exclude-red [s]
  (as-> s tmp
      (clojure.string/replace tmp ":" "")
      (read-string tmp)
      (prewalk #(cond
                  (and (map? %) (has-red? %)) nil
                  (map? %) (seq %)
                  :else %) tmp)
      (flatten tmp)
      (filter integer? tmp)
      ))


(defn part-one [input]
  (reduce + (extract-numbers input)))

(defn part-two [input]
  (reduce + (extract-numbers-exclude-red input)))
