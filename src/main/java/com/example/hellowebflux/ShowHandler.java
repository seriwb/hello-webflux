package com.example.hellowebflux;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ShowHandler {

    public RouterFunction<ServerResponse> routes() {
        return route(GET("/show"), this::show);
    }

    Mono<ServerResponse> show(ServerRequest req) {
       return ok().body(Flux.just("Show", "Me", "the", "Magic!"), String.class);
    }
}
