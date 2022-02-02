(ns com.flexiana.scramble-test
  (:require [clojure.test :refer :all])
  (:require [com.flexiana.scramble :as sut]))


(deftest scramble?-test
  (is (true? (sut/scramble? "rekqodlw" "world")))
  (is (true? (sut/scramble? "cedewaraaossoqqyt" "codewars")))
  (is (false? (sut/scramble? "katas" "steak")))
  (is (true? (sut/scramble? "olelh" "hello")))
  (is (false? (sut/scramble? "oelh" "hello"))))