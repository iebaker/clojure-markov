(require '[clojure.string :as str])

(defn tokenize [filename]
    (str/split (str/trimr (slurp filename)) #"\s+"))

(defn pair-to-map [x y] {x [y]})

(defn subsequents [tokens]
    (let [shifted (conj (vec (rest tokens)) nil)]
        (apply merge-with #(vec (concat %1 %2))
        (map pair-to-map tokens shifted))))

(defn generate [graph last-token result]
    (let [chosen-token (rand-nth (graph last-token))]
        (if (nil? chosen-token)
            result
            (recur graph chosen-token (str result " " chosen-token)))))

(defn -main [thearg] 
    (let [tokens (tokenize thearg) graph (subsequents tokens) starting-point (rand-nth tokens)]
        (println (generate graph starting-point starting-point))))

(apply -main *command-line-args*)