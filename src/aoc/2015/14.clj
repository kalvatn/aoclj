(ns aoc.2015.14
  (:require [aoc.core.io :refer :all]))

(def input (lines "2015/14_test.txt"))
; (def input (lines "2015/14.txt"))

(def r #"^(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.$")



(defn parse-reindeer [info]
  (->> (re-seq r info)
      first
      rest
      (map #(if (number? (read-string %)) (read-string %) %))
      (zipmap [:name :speed :stamina :rest])
      (into { :distance 0 })))

(defn parse-reindeers [input]
  (apply merge (map #(hash-map (keyword (:name %)) %) (map parse-reindeer input))))

(def reindeers (parse-reindeers input))

(defn sort-by-distance [reindeers]
  (sort-by :distance reindeers))

(defn lookup [reindeer-name]
  ((keyword reindeer-name) reindeers))

(defn inc-distance [reindeer]
  (update-in reindeer [:distance] + (:speed reindeer)))

(defn reset-stamina [reindeer]
  (assoc reindeer :stamina (dec (:stamina (lookup (:name reindeer))))))

(defn reset-rest [reindeer]
  (assoc reindeer :rest (:rest (lookup (:name reindeer)))))


(defn dec-stamina [reindeer]
  (update-in reindeer [:stamina] dec))

(defn dec-rest [reindeer]
  (update-in reindeer [:rest] dec))


(defn tick [reindeer]
  (cond
    (pos? (:stamina reindeer)) (inc-distance (dec-stamina reindeer))
    (pos? (:rest reindeer)) (dec-rest reindeer)
    :else
    (inc-distance (reset-rest (reset-stamina reindeer)))))


(defn tick-n [n reindeer]
  (last (take n (drop 1 (iterate tick reindeer)))))
  ; (take n (drop 1 (iterate tick reindeer))))

(defn tick-all [n]
  ; (println "second " n)
  (map #(tick-n n %) (vals reindeers)))
      ; clojure.pprint/pprint))

(defn find-leaders [reindeers]
  ; (group-by :distance (sort-by-distance reindeers)))
  (let [g (group-by :distance (sort-by-distance reindeers))]
    (get g (reduce max (keys g)))))

(defn -pprint [v]
  (clojure.pprint/pprint v))

(defn part-one [input]
  (:distance (last (sort-by-distance (tick-all 2503)))))

(defn part-two [input])
  ; (-pprint (map find-leaders (map tick-all (range 1000)))))
  ; (last (map find-leaders (map tick-all (range 1000)))))
  ; (sort-by-distance (tick-all 2503)))
