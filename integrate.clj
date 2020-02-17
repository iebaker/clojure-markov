(defn rand-between [lower-bound upper-bound]
    (let [range-width (- upper-bound lower-bound)]
        (+ lower-bound (* range-width (rand)))))

(defn sample-function [f num-samples lower-bound upper-bound]
    (let [sample-points (for [x (range num-samples)] (rand-between lower-bound upper-bound))]
        (map f sample-points)))

(defn average [vector]
    (if (empty? vector)
        0
        (/ (reduce + vector) (count vector))))

(defn average-value [f lower-bound upper-bound]
    (let [samples (sample-function f 100000 lower-bound upper-bound)]
        (average samples)))

(defn integrate [f lower-bound upper-bound]
    (/ (average-value f lower-bound upper-bound) (- upper-bound lower-bound)))

(println (integrate #(Math/sin %) 0 (Math/PI)))