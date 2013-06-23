(ns ga-clj.core-test
  (:use clojure.test
        ga-clj.core))

(def test-url "http://www.google-analytics.com/__utm.gif?utmac=UT-1234&utmcc=__utma=1.483535636500145214523.1804211063.1371852342.1371852342.15%3B%2B__utmz=1.1371852342.1.1.utmcsr=(direct)%7Cutmccn=(direct)%7Cutmcmd=(none)%3B&utmcs=UTF-8&utme=5(Videos*Play*ID)(123)&utmhid=8229226889&utmhn=mydomain.com&utmip=&utmn=4267207767&utmni=1&utmr=&utmt=event&utmul=en-us&utmwv=4.4sh")

(def event
  {:category "Videos"
   :action "Play"
   :label "ID"
   :value "123"})
;; 
;; (deftest events
;;   (let [event-url* (event-url "UT-1234" "mydomain.com")]
;;     (testing "basic event"
;;       (is (= test-url (event-url* event))))))
