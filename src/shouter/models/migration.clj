(ns shouter.models.migration
    (:require [clojure.java.jdbc :as sql]))

(def db {
     	:classname "org.sqlite.JDBC"
	:subprotocol "sqlite"
	:subname "db/database.db"})

(defn create-shouts [] 
      (try (sql/with-connection db
      	   (sql/create-table :shouts
      			[:id :serial "PRIMARY KEY"]
			[:body :varchar "NOT NULL"]
			[:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
			(catch Exception e (println e)))
)

(defn -main []
      (print "Migrating database ... ") (flush)
      (create-shouts)
      (println "Done ...")
)