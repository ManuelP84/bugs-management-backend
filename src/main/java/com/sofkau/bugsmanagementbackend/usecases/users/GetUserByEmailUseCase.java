package com.sofkau.bugsmanagementbackend.usecases.users;

import com.mongodb.Function;
import com.sofkau.bugsmanagementbackend.dtos.UserAndRolesDTO;
import com.sofkau.bugsmanagementbackend.mapper.UserAndRolesMapper;
import com.sofkau.bugsmanagementbackend.repository.IUserAndRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetUserByEmailUseCase implements Function<String, Mono<UserAndRolesDTO>> {

    private final IUserAndRolesRepository repository;
    private final UserAndRolesMapper mapper;

    @Override
    public Mono<UserAndRolesDTO> apply(String userEmail){
        return repository
                .findByUserEmail(userEmail)
                .onErrorResume(e->{ return Mono.empty(); })
                .switchIfEmpty(Mono.error(()-> new NoSuchElementException()))
                .map(user -> mapper.convertEntityToDto().apply(user));
    }
}
