(ns aoc.core.ext
  (:require [clojure.pprint :refer [pprint]]))

(defn -pprint [v] (pprint v))
(def any? (complement not-any?))
