# ga-clj

A Clojure library designed to send custom events to Google Analytics.

## Usage

``` clj
(def event
  {:category "Test Category"
   :action "Test Action"
   :label nil
   :value nil})

(let [send-google-event* (send-google-event "UA-2285639-8" "teachbanzai.com")]
  (send-google-event* event))
```

## License

Copyright © 2013 Kendall Buchanan

Distributed under the Eclipse Public License, the same as Clojure.
