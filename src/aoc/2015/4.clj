(ns aoc.2015.4
  (:require [aoc.core :refer :all]
            [digest]))

(def input (first-line "2015/4_1.txt"))

(defn first-five [s]
  (subs s 0 5))

(defn starts-with-five-zeroes [s]
  (= "00000" (first-five s)))

(defn md5 [s]
  (digest/md5 s))

(defn find-key [input pattern]
   (->> (range)
       (pmap #(list (md5 (str input %)) %))
       (filter #(re-matches pattern (first %)))
       first
       second))

(defn part-one
  ([] (part-one input))
  ([input] (let [answer (find-key input #"^00000.*")]
             (println "part one" answer)
             answer)))

(defn part-two
  ([] (part-two input))
  ([input] (let [answer (find-key input #"^000000.*")]
             (println "part two" answer)
             answer)))

