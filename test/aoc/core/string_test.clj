(ns aoc.core.string-test
  (:require [clojure.test :refer :all]
            [aoc.core.string :as s]))

(deftest test-char-range
  (is (= [ \a \b \c ] (s/char-range \a \c)))
  (is (= [ \a \b \c \d \e \f \g \h ] (s/char-range \a \h))))

(deftest test-char-range-plus
  (is (= [ \a \b \c \d \e ] (s/char-range-plus \a 4))))

(deftest test-alphabet
  (is (= "abcdefghijklmnopqrstuvwxyz" (s/join (s/alphabet)))))

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

(deftest test-rotate-left
  (is (= "bcdefa" (s/rotate-left "abcdef")))
  (is (= "bcdefa" (s/rotate-left "abcdef" 1))))

(deftest test-rotations-left
  (is (= ["bcdefa" "cdefab" "defabc" "efabcd" "fabcde"] (s/rotations-left "abcdef"))))

(deftest test-rotations-right
  (is (= ["fabcde" "efabcd" "defabc" "cdefab" "bcdefa"] (s/rotations-right "abcdef"))))

(deftest test-padleft
  (is (= "   abcdef" (s/padleft "abcdef" 9 \space))))

(deftest test-padright
  (is (= "abcdef   " (s/padright "abcdef" 9 \space))))


(deftest test-replace-index
  (is (= "abXde" (s/replace-index "abcde" 2 "X")))
  (is (= "aaaaX" (s/replace-index "aaaaa" 4 "X")))
  )

(deftest test-replace-range
  ; 'aaaaa', 0, 2, 'a', 'X'), 'XXXaa')
  ; 'aaaaabbbcccdeeeaaaa', 0, 15, 'a', 'X'), 'XXXXXbbbcccdeeeXaaa')
  (is (= "aaaaa" (s/replace-range "XXXaa" 0 2 "a")))
  (is (= "2222222222222222aaa" (s/replace-range "XXXXXbbbcccdeeeXaaa" 0 15 "2")))
  )


(deftest test-swap-index
  (is (= "abcde" (s/swap-index "adcbe" 1 3)))
  (is (= "4aaa0" (s/swap-index "0aaa4" 0 4)))
  )

(deftest test-reverse-range
  (is (= "cbadef" (s/reverse-range "abcdef" 0 2)))
  (is (= "edcba" (s/reverse-range "abcde" 0 4)))
  (is (= "0123456789" (s/reverse-range "0123654789" 4 6)))
  )

(deftest test-move-index
  (is (= "bdeac" (s/move-index "bcdea" 1 4)))
  (is (= "abdec" (s/move-index "bdeac" 3 0)))
  )
