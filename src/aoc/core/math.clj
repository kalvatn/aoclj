(ns aoc.core.math
  (:require [aoc.core.macros :as macros]
            [clojure.math.numeric-tower :as nt]
            [clojure.math.combinatorics :as c]))

(macros/import-all-vars clojure.math.numeric-tower)
(macros/import-all-vars clojure.math.combinatorics)

(defn combinations-of-size [items n]
  (combinations items n))

(defn all-combinations [items]
  (mapcat #(combinations-of-size items %) (range 1 (inc (count items)))))
