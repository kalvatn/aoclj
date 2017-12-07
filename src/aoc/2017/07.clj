(ns aoc.2017.07
  (:require [aoc.core.io :refer :all]
            [clojure.pprint :refer [pprint]]
            [aoc.core.ext :refer [any?]]
            [aoc.core.string :as s]))

(def input (lines "2017/07.txt"))
; (def input (lines "2017/07_test.txt"))

(def r #"^(\w+) \((\d+)\)(?: -> ([\w\,\s]+)?)*$")

(defn parse-line [line]
  (let [p (rest (first (re-seq r line)))]
    [(first p) (read-string (second p)) (if (nil? (nth p 2)) [] (s/split (nth p 2) #", "))]
  ))

(defn has-children? [e]
  (not (empty? (e 2))))

(defn has-parent? [e all]
  (any? true? (map #(contains? (% 2) (first e)) all)))

(defn roots [all]
  (filter #(empty? ((all %) 2)) (map first all)))

(defn get-parent [n all]
  (first (filter #(contains? (set (% 2)) n) all)))
  ; (filter #(contains? (set (% 2)) n)) all)

(defn get-weight [e]
  (e 1))

(defn get-children [e all]
  (let [child-names (set (e 2))
        children (filter #(contains? child-names (first %)) all)]
    children))

(defn get-child-weights [e all]
  (let [children (get-children e all)]
    (map #(% 1) children)))

(defn sum-weights [all]
  (reduce + (map get-weight all)))


(defn part-one [input]
  (let [all (vec (map parse-line input))
        roots (vec (filter has-children? all))
        root-parents (map (fn [v] [(first v) (get-parent (first v) all)]) roots)
        bottom (filter #(nil? (second %)) root-parents)
        ]
    (first (first bottom))
  ))

(defn to-node [e]
  [(keyword (first e)) { :w (second e) :c (map keyword (e 2))} ])

(def nodes-unordered (into {} (map to-node (map parse-line input))))

(defn map-nodes [node-key]
  (let [node (nodes-unordered node-key)]
    ; (println node-key node)
    {:name node-key :weight (node :w) :children (map map-nodes (:c node)) }))

; (def nodes (map-nodes :tknk))
(def nodes (map-nodes (keyword (part-one input))))


(defn unequal-weights [weights]
  (not= 1 (count (set weights))))

(defn get-weights [node level]
  (if (and (contains? node :children)
           (not (empty? (node :children))))
    (let [child-with-weight (map #(get-weights % (inc level)) (node :children))
          new-node (assoc node :children child-with-weight)
          child-weights (map :total (new-node :children))
          child-sum (reduce + child-weights)
          node-total (+ (node :weight) child-sum)
          ]

      ; (println (node :name) (node :weight) child-weights child-sum)
      ; (when (unequal-weights child-weights)
        (println level (node :name) (node :weight) child-weights child-sum)
        ; )
      (assoc new-node :total node-total))
      { :total (node :weight)}))


(defn remove-stuff [node]
  (if (contains? node :children)
    (dissoc (assoc node :children (map remove-stuff (node :children))) :weight)
    (dissoc node :weight)))

(defn part-two [input]
  (let []
  ; (let [nodes (map-nodes :bpvhwhh)]
    ; (pprint nodes-unordered)
    ; (pprint nodes)
    ; (pprint (get-weights nodes))
    (get-weights nodes 0)
    (pprint "lol")
    ; (pprint (keys nodes))
    )
  )
