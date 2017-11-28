(ns aoc.core.io
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn lines [resource-filename] (line-seq (io/reader (io/resource resource-filename))))
(defn first-line [resource-filename] (first (lines resource-filename)))
