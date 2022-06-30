package com.sofkau.bugsmanagementbackend.repository;

import com.sofkau.bugsmanagementbackend.collections.Bugs;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IBugsRepository extends ReactiveMongoRepository<Bugs, String> {
    Flux<Bugs> findAllByTaskId (String taskId);

    Mono<Bugs> findByBugId (String id);

    Mono<Void> deleteByBugId (String id);

    Flux<Bugs> findAllByProjectId (String projectId);
}
