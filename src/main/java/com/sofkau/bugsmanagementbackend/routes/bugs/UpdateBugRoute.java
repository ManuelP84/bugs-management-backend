package com.sofkau.bugsmanagementbackend.routes.bugs;

import com.sofkau.bugsmanagementbackend.dtos.BugsDTO;
import com.sofkau.bugsmanagementbackend.usecases.bugs.UpdateBugUseCase;
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
public class UpdateBugRoute {

    @Bean
    public RouterFunction<ServerResponse> updateBugRouter (UpdateBugUseCase useCase){
        return route(
                PUT("/v1/api/update/bug").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BugsDTO.class)
                        .flatMap(bugsDTO -> useCase.apply(bugsDTO))
                        .flatMap(bugsDTO -> ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(bugsDTO))
        );
    }
}
