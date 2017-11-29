(ns aoc.2015.11-test
  (:require [clojure.test :refer :all]
            [aoc.2015.11 :refer :all]))

(deftest test-increment-char
  (is (= \b (increment-char \a)))
  (is (= \c (increment-char \b)))
  (is (= \a (increment-char \z))))

(deftest test-increment-string
  (is (= "b" (increment-string "a")))
  (is (= "c" (increment-string "b")))
  (is (= "a" (increment-string "z")))
  (is (= "abc" (increment-string "abb")))
  (is (= "xy" (increment-string "xx")))
  (is (= "xz" (increment-string "xy")))
  (is (= "ya" (increment-string "xz")))
  (is (= "baaa" (increment-string "azzz")))
  )

(deftest test-has-letter-straight?
  (is (= true (has-letter-straight? "abc")))
  (is (= true (has-letter-straight? "hijklmmn")))
  (is (= false (has-letter-straight? "abbceffg")))
  )

(deftest test-has-confusing?
  (is (= true (has-confusing? "iol")))
  (is (= true (has-confusing? "i")))
  (is (= true (has-confusing? "o")))
  (is (= true (has-confusing? "l")))
  (is (= true (has-confusing? "abcdlol")))
  )

(deftest test-has-double-letter-pair?
  (is (= true (has-double-letter-pair? "aabb")))
  (is (= true (has-double-letter-pair? "abcdebbzz")))
  (is (= true (has-double-letter-pair? "abbceffg")))
  (is (= false (has-double-letter-pair? "abbcegjk")))
  )

(deftest test-is-valid?
  (is (= true (is-valid? "abcdffaa")))
  (is (= true (is-valid? "ghjaabcc")))
  (is (= false (is-valid? "hijklmmn")))
  (is (= false (is-valid? "abbceffg")))
  (is (= false (is-valid? "abbcegjk")))
  )

(deftest test-part-one
  (is (= "abcdffaa" (part-one "abcdefgh")))
  ; (is (= "ghjaabcc" (part-one "ghijklmn")))
  )
