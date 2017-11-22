(ns aoc.2015.7
  (:require [aoc.core :refer :all]))

(def input (file-lines "2015/7.txt"))

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
  (into {} (map (comp vec reverse #(split % #" -> ")) instructions)))


(defn part-one
  ([] (part-one input))
  ([input] "part one"))

(defn part-two
  ([] (part-two input))
  ([input] "part two"))
