package com.sofkau.bugsmanagementbackend.routes.projects;

import com.sofkau.bugsmanagementbackend.dtos.ProjectDTO;
import com.sofkau.bugsmanagementbackend.usecases.projects.UpdateProjectUseCase;
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
public class UpdateProjectRoute {

    @Bean
    public RouterFunction<ServerResponse> updateProjectRouter(UpdateProjectUseCase useCase){
        return route(
                PUT("/v1/api/update/project").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProjectDTO.class)
                        .flatMap(projectDTO -> useCase.apply(projectDTO))
                        .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(response))
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
}
