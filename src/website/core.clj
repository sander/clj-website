(ns website.core
  (:require
    [clojure.java.io :as io]
    [ring.adapter.jetty :refer [run-jetty]]
    [bidi.ring :refer [make-handler]]
    [bidi.bidi :refer [match-route]]
    [net.cgrand.enlive-html :as html]
    website.routes
    website.render))

(def renderer (website.render/renderer 'website.core/custom-renderer))

(html/deftemplate
  page (io/resource "page.html")
  [title body]
  [:head :title] (html/content title)
  [:body] (html/html-content body))

(defn handler [{:keys [uri]}]
  (if-let [route (match-route website.routes/routes uri)]
    (let [{:keys [title body]} (renderer route)]
      {:status  200
       :headers {"Content-Type" "text/html; charset=UTF-8"}
       :body    (page title body)})
    {:status  404
     :headers {"Content-Type" "text/html; charset=UTF-8"}
     :body    "Not found"}))

(defn start []
  (defonce server (run-jetty #'handler {:port 3000 :join? false})))
