(ns com.flexiana.scramble)


(defn word-count
  "Takes a string and returns a map where each key is a letter found in string and value is the number of occurrences"
  [s]
  (reduce
    (fn [acc letter]
      (if (contains? acc letter)
        (update acc letter inc)
        (assoc acc letter 1)))
    {} s))

(defn scramble?
  "Checks if s2 can be created by scrambling the letters of s1"
  [s1 s2]
  (let [word-count-s1 (word-count s1)
        word-count-s2 (word-count s2)]
    ; Use every? here for short-circuiting
    (every? (fn [[letter letter-count]]
              (<= letter-count (get word-count-s1 letter 0)))
            word-count-s2)))
