(defproject website
  "0.1.0-SNAPSHOT"

  :dependencies
  [[org.clojure/clojure "1.7.0-beta2"]
   [org.clojure/clojurescript "0.0-3211"]
   [com.cognitect/transit-clj "0.8.271"]
   [com.cognitect/transit-cljs "0.8.207"]
   [ring "1.3.2"]
   [bidi "1.18.10"]
   [enlive "1.1.5"]]

  :plugins
  [[lein-cljsbuild "1.0.5"]]

  :cljsbuild
  {:builds
   {:prerender
    {:source-paths ["src"]
     :compiler {:output-to "target/prerender.js"
                :optimizations :simple}}}})
