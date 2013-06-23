(ns ga-clj.core
  (:require [clojure.string :as string]
            [clj-http.client :as client]))

(def version "0.1.0")
(def host "www.google-analytics.com")
(def beacon "__utm.gif")
(def user-agent (str "GA-Clojure " version))
(def rand-seed (+ 8999999999 1000000000))

(defn basic-random-id []
  (Long/toString (rand rand-seed)))

(defn time-int-as-str []
  (-> (java.util.Date.)
      (.getTime)
      (Long/toString)))

(defn- utma []
  (let [time-now (time-int-as-str)]
    (str "1."
       (basic-random-id)
       "00145214523."
       (Long/toString (+ (rand 1147483647) 1000000000))
       "." time-now "." time-now ".15")))

(defn- utmz []
  (str "1."
       (time-int-as-str)
       ".1.1.utmcsr=(direct)%7Cutmccn=(direct)%7Cutmcmd=(none)%3B"))

(defn event-url [utmac domain]
  (fn [event]
    (str
      "http://"
      host "/"
      beacon "?"
      (string/join "&"
                   [(str "utmac=" utmac)
                    (str "utmcc=__utma=" (utma) "%3B%2B__utmz=" (utmz))
                    "utmcs=UTF-8"
                    (str "utme=5(" (:category event) "*" (:action event) "*"
                         (:label event) ")(" (:value event) ")")
                    (str "utmhid=" (basic-random-id))
                    (str "utmhn=" domain)
                    "utmip="
                    (str "utmn=" (basic-random-id))
                    "utmni=1"
                    "utmr="
                    "utmt=event"
                    "utmul=en-us"
                    "utmwv=4.4sh"]))))

(defn send-google-event [utmac domain]
  (fn [event]
    (let [event-url* (event-url utmac domain)]
      (client/get
        (event-url* event)))))
