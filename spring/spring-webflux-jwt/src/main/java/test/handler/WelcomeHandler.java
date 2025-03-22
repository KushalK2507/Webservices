package test.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class WelcomeHandler {

    public Mono<ServerResponse> welcome(ServerRequest request){
        return ServerResponse.ok().body(BodyInserters.fromValue("Welcome to Reactive World"));
    }

    public Mono<ServerResponse> welcomeUser(ServerRequest request){
        return ServerResponse.ok().body(BodyInserters.fromValue("Hello "+request.pathVariable("userName")+" welcome to Reactive World"));
    }


}
