package com.sofkau.bugsmanagementbackend.routes.bugs;

import com.sofkau.bugsmanagementbackend.dtos.BugsDTO;
import com.sofkau.bugsmanagementbackend.dtos.TaskDTO;
import com.sofkau.bugsmanagementbackend.usecases.bugs.CreateBugUseCase;
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
public class CreateBugRoute {

    @Bean
    public RouterFunction<ServerResponse> createBugRouter(CreateBugUseCase useCase){
        return route(
                POST("/v1/api/save/bug").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BugsDTO.class)
                        .flatMap(bugDto -> useCase.apply(bugDto))
                        .flatMap(bugDto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(bugDto))
        );
    }
}
