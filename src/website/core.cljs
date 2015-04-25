(ns website.core)

(defn custom-renderer [state]
  (str "<!doctype html><pre>" (.replace (pr-str state) \< "&lt;") "</pre>"))
