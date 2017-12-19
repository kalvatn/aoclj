(defproject aoc "0.1.0-SNAPSHOT"
  :description "advent of code solutions in clojure"
  :url "http://github.com/kalvatn/aoclj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ["-Xmx1G"]
  :main aoc.runner
  :global-vars {
                *warn-on-reflection* true
                }
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [digest "1.4.6"]
                 [org.clojure/math.combinatorics "0.1.4"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [potemkin "0.4.4"]
                 [aysylu/loom "1.0.0"]
                 ])
