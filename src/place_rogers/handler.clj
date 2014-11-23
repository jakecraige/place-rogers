(ns place-rogers.handler
  (:require [compojure.core :refer [defroutes routes GET]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [place-rogers.routes.placeholder :refer [placeholder-routes]]))

(defn init []
  (println "place-rogers is starting"))

(defn destroy []
  (println "place-rogers is shutting down"))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes placeholder-routes app-routes)
      (handler/site)
      (wrap-base-url)))
