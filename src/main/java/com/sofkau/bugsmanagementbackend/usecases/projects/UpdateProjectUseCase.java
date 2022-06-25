package com.sofkau.bugsmanagementbackend.usecases.projects;

import com.sofkau.bugsmanagementbackend.dtos.ProjectDTO;
import com.sofkau.bugsmanagementbackend.mapper.ProjectMapper;
import com.sofkau.bugsmanagementbackend.repository.IProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UpdateProjectUseCase implements Function<ProjectDTO, Mono<ProjectDTO>> {

    private final IProjectRepository repository;
    private final ProjectMapper projectMapper;


    @Override
    public Mono<ProjectDTO> apply(ProjectDTO projectDTO) {
        return repository
                .findById(projectDTO.getId())
                .flatMap(project -> repository
                        .save(projectMapper
                                .convertDtoToEntity()
                                .apply(projectDTO))
                        .map(project1 -> projectMapper
                                .convertEntityToDto()
                                .apply(project1)))
                .switchIfEmpty(Mono.error(new IllegalStateException("Project does not exist")));
    }
}
