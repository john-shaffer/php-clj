# php-clj [![Build Status](https://travis-ci.org/mudge/php-clj.png?branch=master)](https://travis-ci.org/mudge/php-clj)

A Clojure library to deserialize PHP as generated by
[`serialize`](http://php.net/manual/en/function.serialize.php) into Clojure
data structures and vice versa.

**Current version**: 0.3.0
**Clojure version support:** 1.4, 1.5

## Usage

```clojure
(ns foo.bar
  (:require [php_clj.core :refer [php->clj clj->php]]))

(php->clj "s:18:\"Café Scientifique\";")
;; => "Café Scientifique"

(php->clj "i:1337;")
;; => 1337

(php->clj "a:2:{s:4:\"Wöo\";a:3:{i:0;i:1;i:1;i:2;i:2;i:3;}s:3:\"Bar\";b:0;}")
;; => #ordered/map (["Wöo" #ordered/map ([0 1] [1 2] [2 3])] ["Bar" false])

(clj->php "Café")
;; => "s:5:\"Café\";"

(clj->php {"name" "Bob"})
;; => "a:1:{s:4:\"name\";s:3:\"Bob\";}"
```

## A Note on PHP Arrays

As [PHP's Arrays](http://www.php.net/manual/en/language.types.array.php) are
actually ordered maps, converting an array such as `array(1, 2, 3)` will
result in an [ordered map](https://github.com/flatland/ordered) equivalent to
`{0 1, 1 2, 2 3}`. Note that Clojure's standard map implementation does not
retain insertion order (and [ArrayMaps are only suitable for "very small
maps"](http://clojure.org/data_structures#Data%20Structures-ArrayMaps)) hence
the use of the `ordered-map` type.

## Installation

php-clj is available on [Clojars](https://clojars.org/php-clj), add the
following to your [Leiningen](https://github.com/technomancy/leiningen)
dependencies:

```clojure
[php-clj "0.3.0"]
```

## References

* [Arto Bendiken's `php-s11n`](http://wiki.call-cc.org/eggref/4/php-s11n);
* [Brad Koch's Stack Overflow discussion "Parsing serialized PHP data with BNF
  using
  Instaparse"](http://stackoverflow.com/questions/18518499/parsing-serialized-php-data-with-bnf-using-instaparse).

## License

Copyright © 2014 Paul Mucur.

Distributed under the Eclipse Public License, the same as Clojure.
