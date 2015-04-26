(ns website.core)

(defn custom-renderer [{:keys [handler] :as state}]
  {:title (name handler)

   :body
   (str "<h1>" (name handler) "</h1>"
        "<pre>" (.replace (pr-str state) \< "&lt;") "</pre>")})
