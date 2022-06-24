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
public class CreateProjectUseCase implements Function<ProjectDTO, Mono<ProjectDTO>> {

    private final IProjectRepository repository;
    private final ProjectMapper projectMapper;


    @Override
    public Mono<ProjectDTO> apply(ProjectDTO projectDTO) {
        return repository
                .save(projectMapper.convertDtoToEntity().apply(projectDTO))
                .map(project -> projectMapper.convertEntityToDto().apply(project));
    }
}
