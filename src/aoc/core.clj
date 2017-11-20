(ns aoc.core
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn file-lines [resource-filename] (line-seq (io/reader (io/resource resource-filename))))

(defn first-line [resource-filename] (first (file-lines resource-filename)))

(defn split
  ([s] (split s #" "))
  ([s sep] (string/split s sep)))

(defn count-occurrences [s item]
  (get (frequencies s) item))

(def any? (complement not-any?))

(defn char-range [start end]
  (map char (range (int start) (inc (int end)))))

(defn alphabet []
  (char-range \a \z))

