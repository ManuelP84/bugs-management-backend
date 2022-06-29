package com.sofkau.bugsmanagementbackend.usecases.tasks;

import com.sofkau.bugsmanagementbackend.repository.ITaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DeleteTaskByIdUseCase implements Function<String, Mono<Void>> {

    private final ITaskRepository repository;


    @Override
    public Mono<Void> apply(String id) {
        return repository
                .deleteById(id);
    }
}
