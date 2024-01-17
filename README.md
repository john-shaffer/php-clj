# php-clj

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.john-shaffer/php-clj.svg)](https://clojars.org/org.clojars.john-shaffer/php-clj)

***This is a fork of php-clj that has been updated for compatibility with newer versions of the [ordered](https://github.com/clj-commons/ordered) dependency. The tests have been updated to pass in Clojure 1.10.1. If you are getting an error like `clojure.lang.ExceptionInfo: Conflicting data-reader mapping {:url #object[java.net.URL 0x42039326 "jar:file:/home/john/.m2/repository/org/flatland/ordered/1.5.7/ordered-1.5.7.jar!/data_readers.clj"], :conflict ordered/set, :mappings {ordered/set #'ordered.set/into-ordered-set, ordered/map #'ordered.map/ordered-map}}` with the mainline php-clj, then this fork is what you need.***

A Clojure library to deserialize PHP as generated by
[`serialize`](http://php.net/manual/en/function.serialize.php) into Clojure
data structures and vice versa.

**Current version**: 1.2.0

**Supported Clojure versions:** 1.10 or later

## Usage

```clojure
(ns foo.bar
  (:require [php_clj.core :refer [php->clj clj->php]]))

(php->clj "s:18:\"Café Scientifique\";")
;; => "Café Scientifique"

(php->clj "i:1337;")
;; => 1337

(php->clj "a:2:{s:4:\"Wöo\";a:3:{i:0;i:1;i:1;i:2;i:2;i:3;}s:3:\"Bar\";b:0;}")
;; => #ordered/map (["Wöo" [1 2 3]] ["Bar" false])

(clj->php "Café")
;; => "s:5:\"Café\";"

(clj->php {"name" "Bob"})
;; => "a:1:{s:4:\"name\";s:3:\"Bob\";}"
```

## A Note on PHP Arrays

As [PHP's Arrays](http://www.php.net/manual/en/language.types.array.php) are
actually ordered maps, converting an array such as:

```php
array(
    "name" => "Bob",
    "age" => 42
)
```

Will result in an [ordered
map](https://github.com/clj-commons/ordered) equivalent to the following:

```clojure
{"name" "Bob", "age" 42}
```

Note that Clojure's standard map implementation does not retain
insertion order (and [ArrayMaps are only suitable for "very small
maps"](http://clojure.org/data_structures#Data%20Structures-ArrayMaps)) hence
the use of the `ordered-map` type.

Arrays with consecutive indices starting at 0 such as

```php
array(
    0 => "a",
    1 => "b",
    2 => "c"
)
```

Will be converted into vectors like so:

```clojure
["a" "b" "c"]
```

## Installation

Add to deps.edn:

```clojure
org.clojars.john-shaffer/php-clj {:mvn/version "1.2.0"}
```

Or add to project.clj:

```clojure
[org.clojars.john-shaffer/php-clj "1.2.0"]
```

## References

* [Arto Bendiken's `php-s11n`](http://wiki.call-cc.org/eggref/4/php-s11n);
* [Brad Koch's Stack Overflow discussion "Parsing serialized PHP data with BNF
  using
  Instaparse"](http://stackoverflow.com/questions/18518499/parsing-serialized-php-data-with-bnf-using-instaparse).

## License

Copyright © 2014 Paul Mucur.

Distributed under the [Eclipse Public
License](http://www.eclipse.org/legal/epl-v10.html).
