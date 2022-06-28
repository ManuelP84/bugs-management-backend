package com.sofkau.bugsmanagementbackend.usecases.tasks;

import com.sofkau.bugsmanagementbackend.dtos.TaskDTO;
import com.sofkau.bugsmanagementbackend.mapper.TaskMapper;
import com.sofkau.bugsmanagementbackend.repository.ITaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CreateTaskUseCase implements Function<TaskDTO, Mono<TaskDTO>> {

    private final ITaskRepository repository;
    private final TaskMapper mapper;

    @Override
    public Mono<TaskDTO> apply(TaskDTO taskDTO){
        return repository
                .save(mapper.convertDtoToEntity().apply(taskDTO))
                .map(task -> mapper.convertEntityToDto().apply(task));
    }
}
