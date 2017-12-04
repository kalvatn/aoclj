(ns aoc.core.macros
  (:use [potemkin]))

(defmacro import-all-vars
  [namespace]
  `(potemkin/import-vars
    [~namespace
     ~@(map key (ns-publics (the-ns namespace)))]))
