# advent of clojure

https://adventofcode.com/2017 solutions using clojure

## setup

https://leiningen.org/#install

## running from REPL

`lein repl`

### year->day

`(use 'aoc.runner :reload) (run-day <year> <day>)`

### full year

`(use 'aoc.runner :reload) (run-year <year>)`

## running from JAR

`lein uberjar`

### year->day

`java -jar target/aoc-0.1.0-SNAPSHOT-standalone.jar <year> <day>`

### full year

`java -jar target/aoc-0.1.0-SNAPSHOT-standalone.jar <year>`
