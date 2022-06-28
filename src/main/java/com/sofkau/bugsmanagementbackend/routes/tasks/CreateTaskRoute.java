package com.sofkau.bugsmanagementbackend.routes.tasks;

import com.sofkau.bugsmanagementbackend.dtos.TaskDTO;
import com.sofkau.bugsmanagementbackend.dtos.UserAndRolesDTO;
import com.sofkau.bugsmanagementbackend.usecases.tasks.CreateTaskUseCase;
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
public class CreateTaskRoute {

    @Bean
    public RouterFunction<ServerResponse> createTaskRouter(CreateTaskUseCase useCase){
        return route(
                POST("/v1/api/save/task").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TaskDTO.class)
                        .flatMap(taskDto -> useCase.apply(taskDto))
                        .flatMap(taskDto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(taskDto))
        );
    }
}
