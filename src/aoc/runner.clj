(ns aoc.runner
  (:require [aoc.core.io :refer :all]))

(defn- lookup [year day fn-or-var]
  (let [module (format "aoc.%4d.%02d" year day)]
    (require (symbol module) :reload)
    (resolve (symbol (str module "/" fn-or-var)))))

(defn- get-input [year day]
  (deref (lookup year day "input")))

(defn- print-result-with-time [prefix function input]
  (time (print (format "%25s : %-15s" prefix (function input)))))

(defn- print-error [year day error]
   (println (format "error running aoc.%4d.%02d : %s" year day (.getMessage error))))

(defn run-day
  ([year day]
   (try
     (run-day year day (get-input year day))
     (catch Exception e (print-error year day e))))
  ([year day input]
   (try
     (let [modulename (format "aoc.%4d.%02d" year day)
           pt1-fn (lookup year day "part-one")
           pt2-fn (lookup year day "part-two")]
       (print (with-out-str (print-result-with-time (str modulename "/part-one") pt1-fn input)))
       (println (with-out-str (print-result-with-time (str modulename "/part-two") pt2-fn input))))
     (catch Exception e (print-error year day e)))))

(defn run-year [year]
  (map #(run-day year %) (range 1 26)))
