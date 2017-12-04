(ns aoc.core.math
  (:require [aoc.core.macros :as macros]
            [aoc.core.string :as s]
            [clojure.math.numeric-tower :as nt]
            [clojure.math.combinatorics :as c]))

(macros/import-all-vars clojure.math.numeric-tower)
(macros/import-all-vars clojure.math.combinatorics)

(defn all-combinations [items]
  (mapcat #(combinations items %) (range 1 (inc (count items)))))

