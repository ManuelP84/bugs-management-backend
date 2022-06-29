package com.sofkau.bugsmanagementbackend.usecases.bugs;

import com.sofkau.bugsmanagementbackend.repository.IBugsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DeleteBugUseCase implements Function<String, Mono<Void>> {

    private final IBugsRepository repository;

    @Override
    public Mono<Void> apply(String id) {
        return repository
                .findByBugId(id)
                .switchIfEmpty(Mono.error(new IllegalStateException("Bug does not exist!")))
                .flatMap(bugs -> repository.deleteByBugId(id));
    }
}
