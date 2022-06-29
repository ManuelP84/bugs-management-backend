package com.sofkau.bugsmanagementbackend.usecases.users;

import com.sofkau.bugsmanagementbackend.dtos.UserAndRolesDTO;
import com.sofkau.bugsmanagementbackend.mapper.UserAndRolesMapper;
import com.sofkau.bugsmanagementbackend.repository.IUserAndRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase implements Supplier<Flux<UserAndRolesDTO>> {

    private final IUserAndRolesRepository repository;
    private final UserAndRolesMapper mapper;

    @Override
    public Flux<UserAndRolesDTO> get() {
        return repository
                .findAll()
                .map(user -> mapper
                        .convertEntityToDto()
                        .apply(user));
    }
}
