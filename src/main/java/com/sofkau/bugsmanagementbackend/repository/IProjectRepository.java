package com.sofkau.bugsmanagementbackend.repository;

import com.sofkau.bugsmanagementbackend.collections.Project;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends ReactiveMongoRepository<Project, String> {
}
