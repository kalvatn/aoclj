(ns aoc.core.strings)

(defn char-range [start end]
  (map char (range (int start) (inc (int end)))))

(defn alphabet []
  (char-range \a \z))

(defn split
  ([s] (split s #" "))
  ([s pattern] (clojure.string/split s pattern)))

(defn count-occurrences [s item]
  (get (frequencies s) item))
