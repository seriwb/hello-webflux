package com.example.hellowebflux;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.BodyInserters.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Controller
public class HelloRouterFunctions {

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route(GET("/"),
                req -> ok().body(Flux.just("Hello", "World"), String.class));
//        return route(GET("/"), req -> ok().syncBody("Hello World!"));     // 同期レスポンスを返す場合
//        return route(GET("/"), req -> ok().body(
//                fromPublisher(Flux.just("Hello", "World!"), String.class)));  // レスポンスにこだわる場合
    }

    @Bean
    RouterFunction<ServerResponse> handle(HelloHandler helloHander) {
        return route(GET("/handle"), helloHander::hello);
    }

    @Bean
    RouterFunction<ServerResponse> show(ShowHandler showHandler, HelloHandler helloHander) {
        return showHandler.routes().and(route(GET("/handle"), helloHander::hello));
    }
}
