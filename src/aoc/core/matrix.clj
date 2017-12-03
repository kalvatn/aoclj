(ns aoc.core.matrix)

(defn pprint-matrix [matrix]
  (println (for [row matrix] (pr-str row "\n"))))

(defn pprint-number-matrix [matrix digit-size]
  (println
    (apply str
      (reduce concat
              (interpose "\n"
                         (map #(map (fn [v] (format (str "%0" digit-size "d ") v)) %) matrix))))))


(defn range-pairs [y1 x1 y2 x2]
  (for [y (range y1 (inc y2)) x (range x1 (inc x2))] [y x]))

(defn vec-2d [columns rows initial-value]
  (vec (take columns (repeat (vec (take rows (repeat initial-value)))))))


(defn row [matrix y]
  (nth matrix y))

(defn lookup [matrix y x]
  (nth (row matrix y) x))

(defn assign [matrix [y x value]]
  (assoc matrix y (assoc (nth matrix y) x value)))

(defn assign-range [ matrix [ y1 x1 y2 x2 value] ]
  (reduce assign matrix (map #(conj % value) (range-pairs y1 x1 y2 x2))))

(defn assign-with-fn [matrix [y x value-fn]]
  (assign matrix [y x (value-fn (lookup matrix y x))]))

(defn assign-range-with-fn [ matrix [ y1 x1 y2 x2 value-fn] ]
  (reduce assign-with-fn matrix (map #(conj % value-fn) (range-pairs y1 x1 y2 x2))))
