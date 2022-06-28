package com.sofkau.bugsmanagementbackend.usecases.projects;

import com.sofkau.bugsmanagementbackend.mapper.ProjectMapper;
import com.sofkau.bugsmanagementbackend.repository.IProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DeleteProjectUseCase implements Function<String, Mono<Void>> {

    private final IProjectRepository repository;
    private final ProjectMapper projectMapper;

    @Override
    public Mono<Void> apply(String id) {
        System.out.println(id);

        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalStateException("Project does not exist!")))
                .flatMap(project -> project.getState().equals("CREATED") ? repository.deleteById(project.getId()) : Mono.error(new IllegalAccessException("Not possible to delete the project!")));
    }
}
