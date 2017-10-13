# Spring-WebFluxの確認

元記事：[はじめてのSpring WebFlux (その1 - Spring WebFluxを試す)](https://blog.ik.am/entries/417)

@Controllerの形式でWebFluxを試す場合は、com.example.bk.HelloControllerを
com.example.hellowebflux.HelloControllerに移動して実行してください。

```
curl -v localhost:8080
curl -v localhost:8080 -H 'Accept: text/event-stream'
curl -v localhost:8080 -H 'Accept: application/stream+json'

curl -v localhost:8080/stream
curl -v localhost:8080/stream -H 'Accept: text/event-stream'
curl -v localhost:8080/stream -H 'Accept: application/stream+json'

curl localhost:8080/echo -d hoge

curl -v localhost:8080/stream -d '{"value":1}{"value":2}{"value":3}' -H 'Content-Type: application/stream+json' -H 'Accept: text/event-stream'
```
