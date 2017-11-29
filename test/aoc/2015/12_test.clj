(ns aoc.2015.12-test
  (:require [clojure.test :refer :all]
            [aoc.2015.12 :refer :all]))

(deftest test-extract-numbers
  (is (= [2 4] (extract-numbers "{\"a\":2,\"b\":4}")))
  (is (= [1 2 3] (extract-numbers "[1,2,3]"))))

(deftest test-extract-numbers-exclude-red
  (is (= [2 4] (extract-numbers-exclude-red "{\"a\":2,\"b\":4}")))
  (is (= [1 2 3] (extract-numbers-exclude-red "[1,2,3]")))
  (is (= [1 3] (extract-numbers-exclude-red "[1,{\"c\":\"red\",\"b\":2},3]")))
  (is (= [] (extract-numbers-exclude-red "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")))
  (is (= [1 5] (extract-numbers-exclude-red "[1,\"red\",5]")))
  )

(deftest test-part-one
  (is (= 6 (part-one "{\"a\":2,\"b\":4}")))
  (is (= 6 (part-one "[1,2,3]")))
  (is (= 3 (part-one "[[[3]]]")))
  (is (= 3 (part-one "{\"a\":{\"b\":4},\"c\":-1}")))
  (is (= 0 (part-one "{\"a\":[-1,1]}")))
  (is (= 0 (part-one "[-1,{\"a\":1}]")))
  )

(deftest test-part-two
  (is (= 6 (part-two "{\"a\":2,\"b\":4}")))
  (is (= 6 (part-two "[1,2,3]")))
  (is (= 3 (part-two "[[[3]]]")))
  (is (= 3 (part-two "{\"a\":{\"b\":4},\"c\":-1}")))
  (is (= 0 (part-two "{\"a\":[-1,1]}")))
  (is (= 0 (part-two "[-1,{\"a\":1}]")))
  (is (= 4 (part-two "[1,{\"c\":\"red\",\"b\":2},3]")))
  (is (= 0 (part-two "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")))
  (is (= 6 (part-two "[1,\"red\",5]")))
  )

