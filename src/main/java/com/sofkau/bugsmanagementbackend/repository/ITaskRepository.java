package com.sofkau.bugsmanagementbackend.repository;

import com.sofkau.bugsmanagementbackend.collections.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITaskRepository extends ReactiveMongoRepository<Task, String> {
    Flux<Task> findAllByProjectId (String projectId);
}
