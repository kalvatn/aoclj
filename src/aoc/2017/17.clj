(ns aoc.2017.17
  (:require [aoc.core.io :refer :all]))

(def input (first-line "2017/17.txt"))

(defrecord Buffer [values current-index steps-per-insert])

(defn spin [buffer-state new-value]
  (let [values (:values buffer-state)
        current-index (:current-index buffer-state)
        steps (:steps-per-insert buffer-state)
        new-index (last (take (+ (inc current-index) steps) (cycle (range (count values)))))
        new-values (flatten [ (take (inc new-index) values) new-value (drop (inc new-index) values)])
        ]
    (when (zero? (mod new-value 10000)) (println new-value))
    (->Buffer new-values (inc new-index) steps)))

; (def step 3)
(def step (read-string input))
(def initial-buffer (->Buffer [0] 0 step))

(defn part-one [input]
  (let [new-buffer (reduce spin initial-buffer (range 1 (inc 2017)))]
    (first (drop (inc (:current-index new-buffer)) (:values new-buffer)))))

(defn part-two [input]
  (loop [n 1
         i 0
         a 0]
    (let [ni (int (mod (+ (inc i) step) n))]
      (if (> n 50000000) a
        (recur (inc n)
               ni
               (if (zero? ni) n a))))))

