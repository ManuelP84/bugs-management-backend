package com.sofkau.bugsmanagementbackend.routes.tasks;

import com.sofkau.bugsmanagementbackend.usecases.tasks.DeleteTaskByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteTaskByIdRoute {

    @Bean
    public RouterFunction<ServerResponse> deleteTaskRouter (DeleteTaskByIdUseCase useCase){
        return route(
                DELETE("/v1/api/delete/task/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.ACCEPTED)
                        .body(BodyInserters
                                .fromPublisher(useCase.apply(request.pathVariable("id")), Void.class))
        );
    }
}
