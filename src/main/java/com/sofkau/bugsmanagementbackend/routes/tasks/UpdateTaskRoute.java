package com.sofkau.bugsmanagementbackend.routes.tasks;

import com.sofkau.bugsmanagementbackend.dtos.TaskDTO;
import com.sofkau.bugsmanagementbackend.usecases.tasks.UpdateTaskUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateTaskRoute {

    @Bean
    public RouterFunction<ServerResponse> updateTaskRouter(UpdateTaskUseCase useCase){
        return route(
                PUT("/v1/api/update/task").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TaskDTO.class)
                        .flatMap(taskDto -> useCase.apply(taskDto))
                        .flatMap(taskDto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(taskDto))
        );
    }
}
