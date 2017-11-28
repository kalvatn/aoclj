(ns aoc.core.strings-test
  (:require [clojure.test :refer :all]
            [aoc.core.strings :refer :all]))

(deftest test-char-range
  (is (= [ \a \b \c ] (char-range \a \c)))
  (is (= [ \a \b \c \d \e \f \g \h ] (char-range \a \h))))

(deftest test-alphabet
  (is (= "abcdefghijklmnopqrstuvwxyz" (apply str (alphabet)))))

(deftest test-split
  (testing "split string"
    (is (= ["abc" "123" "1234"] (split "abc 123 1234")))
    (is (= ["abc" "123" "1234"] (split "abc\n123\n1234" #"\n")))))

(deftest test-count-occurrences
  (testing "count occurrences"
    (is (= 2 (count-occurrences "aabc" \a)))
    (is (= 2 (count-occurrences "((())" \))))
    (is (= 3 (count-occurrences [:a :a :b :b :c :c :c] :c)))))
