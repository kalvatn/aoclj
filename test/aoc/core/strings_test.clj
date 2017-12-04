(ns aoc.core.strings-test
  (:require [clojure.test :refer :all]
            [aoc.core.strings :as s]))

(deftest test-char-range
  (is (= [ \a \b \c ] (s/char-range \a \c)))
  (is (= [ \a \b \c \d \e \f \g \h ] (s/char-range \a \h))))

(deftest test-char-range
  (is (= [ \a \b \c \d \e ] (s/char-range-plus \a 4))))

(deftest test-alphabet
  (is (= "abcdefghijklmnopqrstuvwxyz" (apply str (s/alphabet)))))

(deftest test-count-occurrences
  (testing "count occurrences"
    (is (= 2 (s/count-occurrences "aabc" \a)))
    (is (= 2 (s/count-occurrences "((())" \))))
    (is (= 3 (s/count-occurrences [:a :a :b :b :c :c :c] :c)))))

(deftest test-generate-keywords
  (is (= [:a :b :c :d :e] (s/generate-keywords 5))))

(deftest test-map-by-char
  (is (= {:a 1 :b 2 :c 3 :d 4 :e 5} (s/map-by-char [1 2 3 4 5]))))

(deftest test-rotate-right
  (is (= "fabcde" (s/rotate-right "abcdef")))
  (is (= "fabcde" (s/rotate-right "abcdef" 1))))

(deftest test-rotate-right
  (is (= "bcdefa" (s/rotate-left "abcdef")))
  (is (= "bcdefa" (s/rotate-left "abcdef" 1))))

(deftest test-rotations-left
  (is (= ["bcdefa" "cdefab" "defabc" "efabcd" "fabcde"] (s/rotations-left "abcdef"))))

(deftest test-rotations-left
  (is (= ["fabcde" "efabcd" "defabc" "cdefab" "bcdefa"] (s/rotations-right "abcdef"))))

(deftest test-padleft
  (is (= "   abcdef" (s/padleft "abcdef" 9 \space))))

(deftest test-padright
  (is (= "abcdef   " (s/padright "abcdef" 9 \space))))
