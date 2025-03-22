package org.kushal.reactive.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class GlobalWebExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(org.springframework.web.server.ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof ClientNotFoundException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            var message = "Invalid Client id ".getBytes(StandardCharsets.UTF_8);
            var errorMessage = exchange.getResponse().bufferFactory().wrap(message);
            return exchange.getResponse().writeWith(Mono.just(errorMessage));
        }
        return Mono.error(ex); // Propagate the error if it's not handled here
    }
}
