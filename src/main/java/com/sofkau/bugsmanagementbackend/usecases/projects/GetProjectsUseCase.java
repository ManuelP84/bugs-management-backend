package com.sofkau.bugsmanagementbackend.usecases.projects;

import com.sofkau.bugsmanagementbackend.dtos.ProjectDTO;
import com.sofkau.bugsmanagementbackend.mapper.ProjectMapper;
import com.sofkau.bugsmanagementbackend.repository.IProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class GetProjectsUseCase implements Supplier<Flux<ProjectDTO>> {

    private final IProjectRepository repository;
    private final ProjectMapper projectMapper;


    @Override
    public Flux<ProjectDTO> get() {
        return repository
                .findAll()
                .map(project -> projectMapper
                        .convertEntityToDto()
                        .apply(project));
    }
}
