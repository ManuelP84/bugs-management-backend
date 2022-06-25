package com.sofkau.bugsmanagementbackend.usecases.users;

import com.mongodb.Function;
import com.sofkau.bugsmanagementbackend.dtos.UserAndRolesDTO;
import com.sofkau.bugsmanagementbackend.mapper.UserAndRolesMapper;
import com.sofkau.bugsmanagementbackend.repository.IUserAndRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase implements Function<UserAndRolesDTO, Mono<UserAndRolesDTO>> {

    private final IUserAndRolesRepository repository;
    private final UserAndRolesMapper mapper;

    @Override
    public Mono<UserAndRolesDTO> apply(UserAndRolesDTO userAndRolesDTO){
        return repository
                .save(mapper.convertDtoToEntity().apply(userAndRolesDTO))
                .map(user -> mapper.convertEntityToDto().apply(user));
    }
}
