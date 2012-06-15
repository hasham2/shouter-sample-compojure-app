(ns shouter.core
    (:use [compojure.core :only [:defroutes]])
    (:require [compojure.route :as route]
    	      [compojure.handler :as handler]
	      [ring.adapter.jetty :as ring]
	      [shouter.controllers.shouts]
	      [shouter.views.layout :as layout])

;;lets see where this code goes

(def testdata {
      :date    "2012-01-01"
      :body    "Happy New Year everyone"})

(def db {
      :classname	"org.sqlite.JDBC"
      :subprotocol	"sqlite"
      :subname		"db/database.db"
      })

(defn create-db []
      (try (with-connection db 
      	   (create-table :shouts 
	   		 [:date :text] 
			 [:body :text]))
	(catch Exception e (println e)))
)

(create-db)

(with-connection db 
      (insert-records :shouts testdata))

(def output 
     (with-connection db 
     		      (with-query-results rs ["select * from shouts"] 
		      			  (doall rs))))
(keys (first output))

;; end code

(defn app [req]
      {:status 200
      :headers {"Content-Type" "text/plain"}
      :body "Hello World"})

(defn start [] 
      (ring/run-jetty #'routes {:port 8080 :join? false}))

;;(defn -main [port]
;;      (run-jetty app {:port (Integer. port)}))
