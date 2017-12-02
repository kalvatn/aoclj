(ns aoc.core.math
  (:require [clojure.math.combinatorics :as combo]
            [clojure.math.numeric-tower :as math]))

(defn combinations-of-size [items n]
  (combo/combinations items n))

(defn all-combinations [items]
  (mapcat #(combinations-of-size items %) (range 1 (inc (count items)))))

(defn abs [n]
  (math/abs n))
