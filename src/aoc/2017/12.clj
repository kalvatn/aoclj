(ns aoc.2017.12
  (:require [aoc.core.io :refer :all]
            [aoc.core.math :as m]
            [aoc.core.string :as s]
            [loom.graph :refer [digraph add-nodes add-edges]]
            [loom.alg :refer [bf-path-bi connected-components]]))

(def input (lines "2017/12.txt"))
; (def input (lines "2017/12_test.txt"))
(def r #"^(\d+) <-> ([\d,\s]+)$")

(defn parse-line [line]
  (let [[left right] (rest (first (re-seq r line)))
        a (read-string left)
        bs (map read-string (s/split right #","))
        ]
    (->> (map #(m/permutations [a %]) bs)
        (apply concat)
        (into #{}))))

(defn parse-input [input]
  (reduce clojure.set/union (map parse-line input)))

(def connections (parse-input input))
(def nodes (into #{} (flatten (seq connections))))
(def graph (reduce add-edges (reduce add-nodes (digraph) nodes) connections))

(defn groups []
  (connected-components graph))

(defn part-one [input]
  (count (remove nil? (map (partial bf-path-bi graph 0) nodes))))

(defn part-two [input]
  (count (connected-components graph)))
