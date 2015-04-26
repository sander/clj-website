(ns website.om-helpers
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [bidi.bidi :refer [match-route path-for]]))

(defn on-pop-state [data route-key routes ev]
  (let [path js/location.pathname
        handler (match-route routes path)]
    (om/update! data route-key {:handler handler})))

(defn in-browser-site [data owner {:keys [view route-key routes]}]
  (reify
    om/IInitState
    (init-state [_]
      {:on-pop-state (partial on-pop-state data route-key routes)})
    om/IWillMount
    (will-mount [_]
      (js/addEventListener "popstate" (om/get-state owner [:on-pop-state])))
    om/IWillUnmount
    (will-unmount [_]
      (js/removeEventListener "popstate" (om/get-state owner [:on-pop-state])))
    om/IRender
    (render [_]
      (om/build view data))))