package com.sofkau.bugsmanagementbackend.routes.bugs;

import com.sofkau.bugsmanagementbackend.dtos.BugsDTO;
import com.sofkau.bugsmanagementbackend.usecases.bugs.GetAllBugsByProjectIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class GetAllBugsByProjectIdRoute {

    @Bean
    public RouterFunction<ServerResponse> getAllBugsByProjectIdRouter (GetAllBugsByProjectIdUseCase useCase){
        return route(
                GET("/v1/api/get/project/bugs/{projectId}"),
                request -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("projectId")), BugsDTO.class))
        );
    }
}
