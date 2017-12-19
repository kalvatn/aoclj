(ns aoc.2017.07
  (:require [aoc.core.io :refer :all]
            [clojure.pprint :refer [pprint]]
            [aoc.core.ext :refer [any?]]
            [aoc.core.string :as s]
            [loom.graph :refer [digraph add-nodes add-edges]]
            [loom.alg :refer [topsort bf-traverse]]))

(def input (lines "2017/07.txt"))
; (def input (lines "2017/07_test.txt"))

(def r #"^(\w+) \((\d+)\)(?: -> ([\w\,\s]+)?)*$")

(defn parse-line [line]
  (let [p (rest (first (re-seq r line)))]
    [(first p) (read-string (second p)) (if (nil? (nth p 2)) [] (s/split (nth p 2) #", "))]
  ))

(defrecord Node [id weight children])

(defn create-node-from-input [e]
  (Node. (keyword (first e)) (int (second e)) (map keyword (e 2))))

(defn map-edges [graph node]
  (let [nid (:id node)
        c (:children node)
        edges (map #(vec [nid %]) c)]
    (reduce add-edges graph edges)))

(defn create-graph [nodes]
  (let [g (reduce add-nodes (digraph) (map :id nodes))]
    (reduce map-edges g nodes)))

(def all-nodes (map create-node-from-input (map parse-line input)))
(def all-nodes-by-id (into {} (map (juxt :id identity) all-nodes)))
(def graph (create-graph all-nodes))
(def root (first (topsort graph)))

(defn lookup-node [id]
  (id all-nodes-by-id))

(defn get-total-node-weight [node]
  (let [children (map lookup-node (:children node))
        weight (:weight node)
        child-weights (map get-total-node-weight children)
        total-weight (+ weight (reduce + child-weights))]
    (do
      (if (> (count (set child-weights)) 1)
        (println "unequal" child-weights (map (juxt :id :weight) children))))
    total-weight))

(defn part-one [input]
  root)

(defn part-two [input]
  (doseq [id (bf-traverse graph root)]
    (get-total-node-weight (lookup-node id)))
  )
