package com.sofkau.bugsmanagementbackend.usecases.tasks;

import com.sofkau.bugsmanagementbackend.dtos.TaskDTO;
import com.sofkau.bugsmanagementbackend.mapper.TaskMapper;
import com.sofkau.bugsmanagementbackend.repository.ITaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GetTasksByProjectIdUseCase implements Function<String, Flux<TaskDTO>> {

    private final ITaskRepository repository;
    private final TaskMapper mapper;

    @Override
    public Flux<TaskDTO> apply(String projectId){
        return repository
                .findAllByProjectId(projectId)
                .map(task -> mapper.convertEntityToDto().apply(task));
    }
}
