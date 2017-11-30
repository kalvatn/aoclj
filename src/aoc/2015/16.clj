(ns aoc.2015.16
  (:require [aoc.core.io :refer :all]
            [aoc.core.ext :refer :all]
            [clojure.walk :as w]))

(def input (lines "2015/16.txt"))

(def clues {:children 3
            :cats 7
            :samoyeds 2
            :pomeranians 3
            :akitas 0
            :vizslas 0
            :goldfish 5
            :trees 3
            :cars 2
            :perfumes 1
            })

(defn sanitize-input [line]
  (-> (str "{" line "}")
      (clojure.string/replace ":" "")
      (clojure.string/replace "," "")
      (clojure.string/replace "Sue" "number")))

(defn parse-input [line]
  (w/walk (fn [[k v]] [ (keyword k) v ]) identity (read-string (sanitize-input line))))

(defn clue-fn-part-one [aunt property]
  (or (nil? (property aunt)) (= (property clues) (property aunt))))

(defn clue-fn-part-two [aunt property]
  (or (nil? (property aunt))
      (condp = property
        :cats (< (property clues) (property aunt))
        :trees (< (property clues) (property aunt))
        :pomeranians (> (property clues) (property aunt))
        :goldfish (> (property clues) (property aunt))
        (= (property clues) (property aunt)))))

(defn matches-clues [aunt clue-fn]
  (map #(clue-fn aunt %) (keys clues)))

(defn filter-aunt [aunt clue-fn]
  (if (not-any? false? (matches-clues aunt clue-fn)) aunt nil))


(defn part-one [input]
  (let [aunts (map parse-input input)]
    ((first (filter #(filter-aunt % clue-fn-part-one) aunts)) :number)))

(defn part-two [input]
  (let [aunts (map parse-input input)]
    ((first (filter #(filter-aunt % clue-fn-part-two) aunts)) :number)))
