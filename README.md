# Flexiana Scramble Assignement

To run the backend:

```shell
clj -M:clj:dev
```

and then run in the REPL to start the HTTP server 
```clojure
(start)
```

To run the frontend:
```shell
clj -M:cljs:dev-cljs:shadow-cljs watch app
```

and open http://localhost:8700 in your browser.