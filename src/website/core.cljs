(ns website.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [bidi.bidi :refer [match-route]]
    website.routes))

(defn custom-renderer [{:keys [route] :as state}]
  (let [{:keys [handler]} route]
    {:title (name handler)

     :body
            (str "<h1>" (name handler) "</h1>"
                 "<pre>" (.replace (pr-str state) \< "&lt;") "</pre>")}))

(defn site [data owner]
  (reify
    om/IRenderState
    (render-state [_ {:keys [hover?]}]
      (dom/main
        #js {:style #js {:backgroundColor (if hover? "yellow" "white")}
             :onMouseOver #(om/set-state! owner :hover? true)
             :onMouseOut #(om/set-state! owner :hover? false)}
        (dom/nav
          nil
          (dom/a #js {:href "/index.html"} "Home")
          (dom/a #js {:href "/articles/index.html"} "Articles"))
        (dom/pre nil (pr-str data))))))
(defn site-renderer [data]
  {:title "Untitled"
   :body  (dom/render-to-str (om/build site data))})
(defn render-in-browser []
  (om/root site {:route (match-route website.routes/routes js/location.pathname)} {:target (js/document.querySelector "body > main")}))