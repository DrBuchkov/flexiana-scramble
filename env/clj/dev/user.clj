(ns user
  (:require [com.flexiana.core :refer [start-app! stop-app!]]
            [clojure.tools.namespace.repl :as tn]
            [com.flexiana.http]))


(defn refresh-ns
  "Refresh/reloads all the namespace"
  []
  (tn/refresh-all))

(defn start
  "Mount starts life cycle of runtime state"
  []
  (start-app!))

(defn stop
  "Mount stops life cycle of runtime state"
  []
  (stop-app!))

(defn restart-dev
  []
  (stop)
  (refresh-ns)
  (start))
