(ns aoc.2015.5_test
  (:require [clojure.test :refer :all]
            [aoc.2015.5 :refer :all]))

(deftest test-has-disallowed?
  (is (= false (has-disallowed? "aacbyxqp")))
  (is (= true (has-disallowed? "ab")))
  (is (= true (has-disallowed? "cd")))
  (is (= true (has-disallowed? "pq")))
  (is (= true (has-disallowed? "xy"))))

(deftest test-has-three-vowels?
  (is (= true (has-three-vowels? "aaa")))
  (is (= false (has-three-vowels? "abcdefxyz")))
  (is (= true (has-three-vowels? "aeiou")))
  (is (= true (has-three-vowels? "aaa"))))

(deftest test-has-double-letter?
  (is (= true (has-double-letter? "aa")))
  (is (= true (has-double-letter? "bb")))
  (is (= true (has-double-letter? "cc")))
  (is (= true (has-double-letter? "defgg")))
  (is (= true (has-double-letter? "aaxyzz"))))

(deftest test-is-nice
  (is (= true (is-nice? "ugknbfddgicrmopn")))
  (is (= true (is-nice? "aaa")))
  (is (= false (is-nice? "jchzalrnumimnmhp")))
  (is (= false (is-nice? "haegwjzuvuyypxyu")))
  (is (= false (is-nice? "dvszwmarrgswjxmb"))))

(deftest test-is-nicer
  (is (= true (is-nicer? "qjhvhtzxzqqjkmpb")))
  (is (= true (is-nicer? "xxyxx")))
  (is (= false (is-nicer? "uurcxstgmygtbstg")))
  (is (= false (is-nicer? "ieodomkazucvgmuy"))))

(deftest test-has-repeating-sequence
  (is (= true (has-repeating-sequence? "xyxy")))
  (is (= true (has-repeating-sequence? "aabcdefgaa")))
  (is (= false (has-repeating-sequence? "aaa")))
  (is (= false (has-repeating-sequence? "aaabbb")))
  (is (= true (has-repeating-sequence? "aaaa")))
  (is (= true (has-repeating-sequence? "aaaaa")))
  (is (= true (has-repeating-sequence? "yxyx"))))

(deftest test-has-xyx
  (is (= true (has-xyx? "xyx")))
  (is (= true (has-xyx? "abcdefeghi")))
  (is (= true (has-xyx? "abcdefeghi"))))

(deftest test-is-xyx
  (is (= true (is-xyx? "xyx")))
  (is (= true (is-xyx? "aba")))
  (is (= true (is-xyx? "bab")))
  (is (= true (is-xyx? "yxy")))
  (is (= true (is-xyx? "yxy")))
  (is (= true (is-xyx? "yyy")))
  (is (= false (is-xyx? "abb")))
  (is (= false (is-xyx? "bba"))))
