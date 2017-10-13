//package com.example.hellowebflux;
package com.example.bk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Controllerモデル
 *
 * パッケージ階層が、mainメソッドと同列かそれ以下でなければおそらく動かない
 */
@RestController
public class HelloController {

    @GetMapping("/")
    Flux<String> hello() {
        return Flux.just("Hello", "World");
    }

    @GetMapping("/stream")
    Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        return Flux.fromStream(stream.limit(10))    // limitをつけないと延々と返る
                .zipWith(Flux.interval(Duration.ofSeconds(1)))
                .map(tuple -> Collections.singletonMap("value", tuple.getT1()));
    }

    @PostMapping("/echo")
    Mono<String> echo(@RequestBody Mono<String> body) { // Monoでラップすると非同期でchain/composeできる
        return body.map(String::toUpperCase);   // 1件しか返さないときはMonoを使う（Fluxでも動く
    }

    @PostMapping("/stream")
    Flux<Map<String, Integer>> stream(@RequestBody Flux<Map<String, Integer>> body) {
        return body.map(m -> Collections.singletonMap("double", m.get("value") * 2));
    }
}