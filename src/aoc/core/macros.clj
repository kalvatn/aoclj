(ns aoc.core.macros
  (:use [potemkin]))

(defmacro import-all-vars
  [namespace]
  `(potemkin/import-vars
    [~namespace
     ~@(map key (ns-publics (the-ns namespace)))]))

(defmacro import-all-vars-with-exclusions [namespace exclusions]
  (let [publics (ns-publics (the-ns namespace))
        filtered-publics (reduce dissoc publics exclusions)]
  `(potemkin/import-vars [~namespace ~@(keys filtered-publics)])))
