(ns website.core
  (:require
    [clojure.java.io :as io]
    [ring.adapter.jetty :refer [run-jetty]]
    [bidi.ring :refer [make-handler]]
    [bidi.bidi :refer [match-route]]
    [net.cgrand.enlive-html :as html]
    [compojure.core :refer [defroutes]]
    [compojure.route :refer [resources]]
    website.routes
    website.render))

(def renderer (website.render/renderer 'website.core/site-renderer))

(html/deftemplate
  page (io/resource "page.html")
  [title body]
  [:head :title] (html/content title)
  [:main] (html/html-content body))

(defroutes
  routes
  (resources "/"))

(defn render-handler [{:keys [uri]}]
  (if-let [route (match-route website.routes/routes uri)]
    (let [{:keys [title body]} (renderer {:route route})]
      {:status  200
       :headers {"Content-Type" "text/html; charset=UTF-8"}
       :body    (page title body)})))

(defn wrap-renderer [handler]
  #(or (render-handler %) (handler %)))

(def app
  (-> routes
      (wrap-renderer)))

(defn start []
  (def server (run-jetty #'app {:port 3000 :join? false})))
