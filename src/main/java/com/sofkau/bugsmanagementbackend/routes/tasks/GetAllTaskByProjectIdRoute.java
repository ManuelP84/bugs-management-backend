package com.sofkau.bugsmanagementbackend.routes.tasks;

import com.sofkau.bugsmanagementbackend.dtos.TaskDTO;
import com.sofkau.bugsmanagementbackend.usecases.tasks.GetTasksByProjectIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class GetAllTaskByProjectIdRoute {

    @Bean
    public RouterFunction<ServerResponse> getAllTaskByProjectId (GetTasksByProjectIdUseCase useCase){
        return route(
                GET("/v1/api/get/tasks/{projectId}"), request -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("projectId")), TaskDTO.class))
        );

    }
}
