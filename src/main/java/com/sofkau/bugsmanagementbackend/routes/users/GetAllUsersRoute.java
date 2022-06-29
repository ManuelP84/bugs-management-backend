package com.sofkau.bugsmanagementbackend.routes.users;

import com.sofkau.bugsmanagementbackend.dtos.UserAndRolesDTO;
import com.sofkau.bugsmanagementbackend.usecases.users.GetAllUsersUseCase;
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
public class GetAllUsersRoute {

    @Bean
    public RouterFunction<ServerResponse> getAllUsersRouter (GetAllUsersUseCase useCase){
        return route(
                GET("/v1/api/users").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), UserAndRolesDTO.class))
        );
    }
}
