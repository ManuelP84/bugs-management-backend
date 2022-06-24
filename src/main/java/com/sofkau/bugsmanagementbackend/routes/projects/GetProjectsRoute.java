package com.sofkau.bugsmanagementbackend.routes.projects;

import com.sofkau.bugsmanagementbackend.dtos.ProjectDTO;
import com.sofkau.bugsmanagementbackend.usecases.projects.GetProjectsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetProjectsRoute {

    @Bean
    public RouterFunction<ServerResponse> getProjectsRouter(GetProjectsUseCase useCase){
        return route(
                GET("/v1/api/projects").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), ProjectDTO.class))
        );
    }
}
