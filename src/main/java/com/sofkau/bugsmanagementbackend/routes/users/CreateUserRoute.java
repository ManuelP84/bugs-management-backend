package com.sofkau.bugsmanagementbackend.routes.users;

import com.sofkau.bugsmanagementbackend.dtos.UserAndRolesDTO;
import com.sofkau.bugsmanagementbackend.usecases.users.CreateUserUseCase;
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
public class CreateUserRoute {

    @Bean
    public RouterFunction<ServerResponse> createUserRouter(CreateUserUseCase useCase){
        return route(
                POST("/v1/api/save/user").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserAndRolesDTO.class)
                        .flatMap(userDto -> useCase.apply(userDto))
                        .flatMap(userDto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(userDto))
        );
    }
}
