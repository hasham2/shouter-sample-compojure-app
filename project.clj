(defproject shouter "0.0.1"
  :description "Shouter app"
  :dependencies [[org.clojure/clojure "1.3.0"]
  		[org.clojure/java.jdbc "0.0.6"]
		[org.xerial/sqlite-jdbc "3.6.13"] 
  		[ring/ring-jetty-adapter "1.0.1"]
		[compojure "0.6.4"]
		[hiccup "0.3.6"]])