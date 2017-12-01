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

(defn run-day
  ([year day] (run-day year day (get-input year day)))
  ([year day input]
   (let [modulename (format "aoc.%4d.%02d" year day)
         pt1-fn (lookup year day "part-one")
         pt2-fn (lookup year day "part-two")]
     (print (with-out-str (print-result-with-time (str modulename "/part-one") pt1-fn input)))
     (print (with-out-str (print-result-with-time (str modulename "/part-two") pt2-fn input))))))
