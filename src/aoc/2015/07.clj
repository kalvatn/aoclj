(ns aoc.2015.07
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]))

(def input (lines "2015/07.txt"))

(def AND "AND")
(def OR "OR")
(def NOT "NOT")
(def LSHIFT "LSHIFT")
(def RSHIFT "RSHIFT")

(defn parse-instruction [instruction]
  ; (println instruction)
  (let [tokens (read-string (str "[" instruction "]"))
        target (last tokens)
        command (drop-last 2 tokens)
        operation (first (filter #(re-matches #"[A-Z]+" (str %)) command))
        ]
    {:tokens tokens
     :target target
     :command command
     :operation operation}))

(defn lookup-register-value [register wire]
  (get register wire))

(defn run-operation [register operation command]
  (condp = operation
    'NOT (println (pr-str command))
    'AND (println (str command))
    'OR (println (str command))
    'LSHIFT (println (str command))
    'RSHIFT (println (str command))))

(defn run-instruction [register instruction]
  (let [parsed (parse-instruction instruction)
        operation (get parsed :operation)
        command (get parsed :command)
        ]
    (condp = operation
      'NOT (println (pr-str command))
      'AND (println (str command))
      'OR (println (str command))
      'LSHIFT (println (str command))
      'RSHIFT (println (str command))
      nil (println (get parsed :value))
      )))

(defn initialize-register [instructions]
  (into {} (map (comp vec reverse #(s/split % #" -> ")) instructions)))


(defn part-one [input])

(defn part-two [input])

