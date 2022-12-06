(defproject aoc "0.1.0-SNAPSHOT"
  :description "advent of code solutions in clojure"
  :url "http://github.com/kalvatn/aoclj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ["-Xmx1G"]
  :main aoc.runner
  ; :global-vars { *warn-on-reflection* true }
  :repl-options { :caught clj-stacktrace.repl/pst+ }
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [digest "1.4.10"]
                 [org.clojure/math.combinatorics "0.1.6"]
                 [org.clojure/math.numeric-tower "0.0.5"]
                 [potemkin "0.4.6"]
                 [aysylu/loom "1.0.2"]
                 ])
