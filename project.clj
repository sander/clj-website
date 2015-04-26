(defproject website
  "0.1.0-SNAPSHOT"

  :dependencies
  [[org.clojure/clojure "1.7.0-beta2"]
   [org.clojure/clojurescript "0.0-3211"]
   [com.cognitect/transit-clj "0.8.271"]
   [com.cognitect/transit-cljs "0.8.207"]
   [ring "1.3.2"]
   [compojure "1.3.3"]
   [bidi "1.18.10"]
   [enlive "1.1.5"]
   [org.omcljs/om "0.8.8"]]

  :plugins
  [[lein-cljsbuild "1.0.5"]]

  :repl-options
  {:init-ns website.core}

  :cljsbuild
  {:builds
   {:dev
    {:source-paths ["src"]
     :compiler {:output-to "resources/public/js/main.js"
                :output-dir "resources/public/js/out"
                :source-map "resources/public/js/out.js.map"
                :asset-path "/js/out"
                :source-map-path "js/out"
                :optimizations :none
                :pretty-print true
                :main website.main}}}})
