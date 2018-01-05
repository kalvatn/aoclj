(ns aoc.2017.18
  (:require [aoc.core.io :refer :all]
            [aoc.core.string :as s]))

(def input (lines "2017/18.txt"))
(def input [
            "set a 1"
            "add a 2"
            "mul a a"
            "mod a 5"
            "snd a"
            "set a 0"
            "rcv a"
            "jgz a -1"
            "set a 1"
            "jgz a -2"])
; (def input ["snd 1"
;             "snd 2"
;             "snd p"
;             "jgz 2 2"
;             "set a 1"
;             "add a 2"
;             "mul a a"
;             "mod a 5"
;             "rcv a"
;             "rcv b"
;             "rcv c"
;             "rcv d"])

(defn parse-line [line]
  (s/split line #" "))

(defn parse-input [input]
  (map parse-line input))

(defn action-value [action]
  (read-string (nth action 2)))

(defn register-value [register x]
  (if (integer? (read-string x))
    (read-string x)
    (register x 0)))

(defn do-action [register action]
  (println action register)
  (let [[op x y] [ (first action) (second action) (nth action 2)]
        xv (register-value register x)
        yv (register-value register y)
        ]
    (condp = op
      "set" (assoc register x yv)
      "add" (assoc register x (+ xv yv))
      "mul" (assoc register x (* xv yv))
      "mod" (assoc register x (mod xv yv)))))

(def actions (vec (parse-input input)))

(defn part-one [input]
  (loop [i 0
         register {}
         last-sound 0]
    (if (or (neg? i) (> i (count actions))) register
      (let [action (actions i)
            [op x] [(first action) (second action)]
            xv (register-value register x)]
        (println i action)
        (Thread/sleep 1000)
        (condp = op
          "jgz" (recur
                  (int (+ i (if (pos? xv) (register-value register (nth action 2)) 1)))
                  register
                  last-sound)
          "snd" (recur
                  (inc i)
                  register
                  (int xv))
          "rcv" (if (pos? xv) last-sound
                  (recur
                    (inc i)
                    register
                    last-sound))
          (recur
            (inc i)
            (do-action register action)
            last-sound))))))

(defrecord Program [id register queue action-index rcv-cnt snd-cnt])

(defn rcv [program target-register]
  ; (println (vals program) "rcv->" target-register)
  (if (empty? (:queue program))
    [program false]
    [(Program. (:id program)
               (assoc (:register program) target-register (peek (:queue program)))
               (pop (:queue program))
               (inc (:action-index program))
               (inc (:rcv-cnt program))
               (:snd-cnt program)) true]))

(defn snd [sender x receiver]
  ; (println (vals sender) "sends" x "to" (vals receiver))
  (let [message (register-value (:register sender) x)
        new-receiver (update receiver :queue conj message)
        new-sender (update (update sender :snd-cnt inc) :action-index inc) ]
    [new-sender new-receiver]))

(defn process-program-action [p1 p2 action]
  (condp = (first action)
    "jgz" (let [[_ x y] action
                xv (register-value (:register p1) x)
                yv (register-value (:register p1) y)
                ni (if (pos? xv) (+ (:action-index p1) yv) (inc (:action-index p1)))]
            [ (assoc p1 :action-index ni) p2])
    "snd" (snd p1 (second action) p2)
    "rcv" (let [[new-p1 p1-rcv-success?] (rcv p1 (second action))]
            [new-p1 p2])
    (do
      (println action)
      [(update (update p1 :register do-action action) :action-index inc) p2])))

(defn part-two [input]
  (loop [p1 (Program. 1 {} '() 0 0 0)
         p2 (Program. 2 {} '() 0 0 0)]
    ; (Thread/sleep 200)
    (let [p1-ai (:action-index p1)
          p2-ai (:action-index p2)]
      (if (not-any? false? (map #(and (not (neg? %)) (< % (count actions))) [p1-ai p2-ai]))
        (let [p1-a (actions p1-ai)
              p2-a (actions p2-ai)]
          (if (and (= ["rcv" "rcv"] [(first p1-a) (first p2-a)]) (empty? (:queue p1)) (empty? (:queue p2)))
            (println (vals p1) (vals p2))
            (let [[new-p1 new-p2] (process-program-action p1 p2 p1-a)
                  [final-p2 final-p1] (process-program-action new-p2 new-p1 p2-a)]
              (recur final-p1 final-p2))))))))
