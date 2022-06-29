package com.sofkau.bugsmanagementbackend.routes.projects;

import com.sofkau.bugsmanagementbackend.usecases.projects.DeleteProjectUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteProjectRoute {

    @Bean
    public RouterFunction<ServerResponse> deleteProjectRouter(DeleteProjectUseCase useCase){
        return route(
                DELETE("/v1/api/delete/project/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(unused -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(IllegalStateException.class, e -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
                        .onErrorResume(IllegalAccessException.class, e -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build())
        );
    }
}