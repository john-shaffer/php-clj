(ns build
  (:refer-clojure :exclude [test])
  (:require [clojure.tools.build.api :as b]
            [org.corfield.build :as bb]))

(def lib 'org.clojars.john-shaffer/php-clj)
(def version "1.3.1")

(defn get-version [opts]
  (str version (when (:snapshot opts) "-SNAPSHOT")))

(defn test [opts]
  (-> opts
      (assoc :command-args ["lein" "midje"])
      b/process)
  opts)

(defn ci "Run the CI pipeline of tests (and build the JAR)." [opts]
  (-> opts
    (assoc :lib lib :version (get-version opts))
    bb/clean
    test
    bb/clean
    (assoc :src-pom "template/pom.xml")
    bb/jar))

(defn deploy "Deploy the JAR to Clojars." [opts]
  (-> opts
    (assoc :lib lib :version (get-version opts))
    bb/deploy))
