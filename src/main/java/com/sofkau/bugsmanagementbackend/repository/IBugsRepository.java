package com.sofkau.bugsmanagementbackend.repository;

import com.sofkau.bugsmanagementbackend.collections.Bugs;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IBugsRepository extends ReactiveMongoRepository<Bugs, String> {
    Flux<Bugs> findAllByTaskId (String taskId);
}
