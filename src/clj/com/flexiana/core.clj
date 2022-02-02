(ns com.flexiana.core
  (:require [clojure.tools.logging :as log]
            [mount.core :as mount])
  (:gen-class))

(defn start-app! []
  (doseq [component (-> (mount/start) :started)]
    (log/info component "started")))

(defn stop-app! []
  (doseq [component (-> (mount/stop)
                        :stopped)]
    (log/info component "stopped")))

(defn -main [& args]
  (start-app!)
  (.addShutdownHook (Runtime/getRuntime) (Thread. ^Runnable stop-app!)))