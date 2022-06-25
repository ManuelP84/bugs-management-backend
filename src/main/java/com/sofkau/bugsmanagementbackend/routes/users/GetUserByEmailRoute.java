package com.sofkau.bugsmanagementbackend.routes.users;

import com.sofkau.bugsmanagementbackend.usecases.users.GetUserByEmailUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Component
public class GetUserByEmailRoute {

    @Bean
    public RouterFunction<ServerResponse> getUserByEmail(GetUserByEmailUseCase useCase){
        return route(GET("/v1/api/get/user/{email}"),
                request -> {
            return useCase.apply(request.pathVariable("email")).onErrorResume(t -> Mono.empty()).flatMap(userDto -> ServerResponse.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(userDto))
                    .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build());
                });
    }
}
