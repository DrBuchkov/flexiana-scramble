(ns com.flexiana.http
  (:require [reitit.coercion.malli :as rc]
            [reitit.ring.coercion :as rrc]
            [reitit.ring :as ring]
            [ring.adapter.jetty :refer [run-jetty]]
            [com.flexiana.middleware :refer [wrap-middleware]]
            [ring.util.http-response :refer [ok]]
            [mount.core :refer [defstate]]
            [com.flexiana.scramble :refer [scramble?]])
  (:import (org.eclipse.jetty.server Server)))

(def routes
  [["/api/scramble" {:coercion   rc/coercion
                     :parameters {:query [:map
                                          [:letters :string]
                                          [:word :string]]}
                     :handler    (fn [{:keys [parameters]}]
                                   (let [{:keys [letters word]} (:query parameters)]
                                     (ok {:result  (scramble? letters word)
                                          :letters letters
                                          :word    word})))}]])

(def handler
  (ring/ring-handler
    (ring/router
      routes
      {:data {:middleware [rrc/coerce-request-middleware
                           rrc/coerce-response-middleware
                           rrc/coerce-exceptions-middleware]}})))

(defstate http-server
  :start (run-jetty (wrap-middleware #'handler) {:port  8080
                                                 :join? false})
  :stop (.stop ^Server http-server))


