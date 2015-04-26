(ns website.routes
  (:require
    [bidi.bidi :as bidi]))

(def routes ["/" {"index.html" :index
                  "articles/" {"index.html" :article-index
                               "article.html" :article}}])