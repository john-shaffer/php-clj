(defproject org.clojars.john-shaffer/php-clj "0.5.1"
  :description "Deserialize PHP into Clojure data structures and back again."
  :url "https://github.com/john-shaffer/php-clj"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.flatland/ordered "1.5.9"]]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:dependencies [[midje "1.9.9"]]
                   :plugins [[lein-midje "3.2.2"]
                             [lein-kibit "0.1.8"]]}})

