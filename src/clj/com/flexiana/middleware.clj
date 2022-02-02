(ns com.flexiana.middleware
  (:require [muuntaja.middleware :as mm]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.http-response :refer [internal-server-error]]
            [clojure.tools.logging :as log]))

(defn wrap-errors [handler]
  (fn [req]
    (try
      (handler req)
      (catch Exception e
        (log/error e)
        (internal-server-error "Oops, something went wrong")))))

(defn wrap-middleware [handler]
  (-> handler
      wrap-params
      wrap-errors
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete :patch])
      mm/wrap-format))
