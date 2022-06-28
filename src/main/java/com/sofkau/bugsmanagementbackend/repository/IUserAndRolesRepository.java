package com.sofkau.bugsmanagementbackend.repository;

import com.sofkau.bugsmanagementbackend.collections.UserAndRoles;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IUserAndRolesRepository extends ReactiveMongoRepository<UserAndRoles, String> {

    Mono<UserAndRoles> findByUserEmail(String userEmail);
}
