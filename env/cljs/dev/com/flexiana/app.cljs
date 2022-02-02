(ns com.flexiana.app
  (:require [devtools.core :as devtools]
            [re-frisk.core :as re-frisk]
            [com.flexiana.core :as core]))


(defn reload! []
  (core/mount!))

(re-frisk/enable)

(enable-console-print!)

(devtools/install!)

(core/mount!)