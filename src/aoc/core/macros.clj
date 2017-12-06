(ns aoc.core.macros
  (:require [potemkin :as p]))

(defmacro import-all-vars
  [namespace]
  `(p/import-vars
    [~namespace
     ~@(map key (ns-publics (the-ns namespace)))]))

(defmacro import-all-vars-with-exclusions [namespace exclusions]
  (let [publics (ns-publics (the-ns namespace))
        filtered-publics (reduce dissoc publics exclusions)]
  `(p/import-vars [~namespace ~@(keys filtered-publics)])))
