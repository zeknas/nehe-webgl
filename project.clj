(defproject nehe-webgl "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1859"]]
  :plugins [[lein-cljsbuild "0.3.3"]]
  :cljsbuild {:builds
              {:main {
                      :source-paths ["src/cljs"]
                      :compiler {
                                 :externs ["resources/babylon-externs.js"]
                                 :output-to "resources/main.js"
                                 :optimizations :advanced
                                 :pretty-print false}}}})
