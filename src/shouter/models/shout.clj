(ns shouter.models.shout
    (:require [clojure.java.jdbc :as sql]))

(def db {
     	:classname "org.sqlite.JDBC"
	:subprotocol "sqlite"
	:subname "db/database.db"})

(defn all [] 
      (sql/with-connection db 
      			   (sql/with-query-results results 
			   ["select * from shouts order by id desc"]
			   (into [] results))))

(defn create [shout]
      (sql/with-connection db
      			   (sql/insert-values :shouts [:body] [shout])))