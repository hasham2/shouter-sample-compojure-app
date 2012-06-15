(ns shouter.core
    (:use [compojure.core :only [defroutes]])
    (:require [compojure.route :as route]
    	      [compojure.handler :as handler]
	      [ring.adapter.jetty :as ring]
	      [shouter.controllers.shouts]
	      [shouter.views.layout :as layout]))

(defroutes routes 
	   shouter.controllers.shouts/routes
	   (route/resources "/")
	   (route/not-found (layout/four-oh-four)))

(def application (handler/site routes))

(defn app [req]
      {:status 200
      :headers {"Content-Type" "text/plain"}
      :body "Hello World"})

(defn start [port] 
      (ring/run-jetty #'application {:port (or port 8080) :join? false}))

(defn -main []
      (let [port (Integer. 8000)]
      (start port)))
