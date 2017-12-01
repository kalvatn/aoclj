(ns aoc.core.strings)

(defn char-range [char-start char-end]
  (map char (range (int char-start) (inc (int char-end)))))

(defn char-range-plus [char-start number-of-chars]
  (char-range char-start (+ (int char-start) number-of-chars)))



(defn alphabet []
  (char-range \a \z))

(defn split
  ([s] (split s #" "))
  ([s pattern] (clojure.string/split s pattern)))

(defn count-occurrences [s item]
  (get (frequencies s) item))

(defn generate-keywords [n]
  (map keyword (map str (char-range-plus \a (dec n)))))

(defn map-by-char [items]
  (zipmap (generate-keywords (count items)) items))
