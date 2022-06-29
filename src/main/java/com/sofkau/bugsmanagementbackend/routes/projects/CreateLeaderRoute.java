package com.sofkau.bugsmanagementbackend.routes.projects;

import com.sofkau.bugsmanagementbackend.usecases.projects.CreateLeaderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateLeaderRoute {

    @Bean
    public RouterFunction<ServerResponse> createLeaderRouter(CreateLeaderUseCase useCase){
        return route(
                POST("/v1/api/save/project/{id}/{leaderEmail}").and(accept(MediaType.APPLICATION_JSON)),
                request -> useCase.apply(request.pathVariable("id"), request.pathVariable("leaderEmail"))
                        .flatMap(unused -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(IllegalStateException.class, e -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
}
