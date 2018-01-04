(ns aoc.2017.16
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]))

(def input (first-line "2017/16.txt"))
; (def input "s1,x3/4,pe/b")

(defn parse-token [token]
  (condp (comp re-matches) token
    #"^s(\d+)$"       :>> #(vec [:spin (read-string (second %))])
    #"^x(\d+)/(\d+)$" :>> #(vec [:exchange (read-string (second %)) (read-string (nth % 2))])
    #"^p(\w+)/(\w+)$" :>> #(vec [:partner (second %) (nth % 2)])))

(defn parse-input [input]
  (map parse-token (s/split input #",")))


(defn apply-spin [programs spin-count]
  (s/rotate-right programs spin-count))

(defn apply-exchange [programs a b]
  (let [indexed (->> programs
                    (map-indexed #(vec [%1 %2]))
                    (into (sorted-map)))
        na (indexed b)
        nb (indexed a)
        changed (assoc (assoc indexed a na) b nb)
        ]
    (apply str (vals changed))))

(defn apply-partner [programs a b]
  (let [indexed (->> programs
                    (map-indexed #(vec [(str %2) %1]))
                    (into {}))
        na (indexed b)
        nb (indexed a)
        changed (assoc (assoc indexed a na) b nb)
        sorted (into (sorted-map-by #(compare (changed %1) (changed %2))) changed)
        ]
    (apply str (keys sorted))))


(defn apply-move [programs move]
  (condp = (first move)
    :spin (apply-spin programs (second move))
    :exchange (apply-exchange programs (second move) (nth move 2))
    :partner (apply-partner programs (second move) (nth move 2))))


(def dance-moves (parse-input input))

(defn part-one [input]
  (reduce apply-move (apply str (s/char-range \a \p)) dance-moves)
  ; (take 3(reductions apply-move (apply str (s/char-range \a \p)) dance-moves))
  )
(def memoized-apply-move (memoize apply-move))
(defn part-two [input]
  (let [initial (apply str (s/char-range \a \p))
        billion 1000000000
        ]
    (loop [n 0
           p initial
           seen (sorted-set)]
      (println p)
      (if (contains? seen p)
        (println seen n (mod billion n) ((vec seen) (mod billion n)))
        (recur (inc n)
               (reduce memoized-apply-move p dance-moves)
               (conj seen p))))))
