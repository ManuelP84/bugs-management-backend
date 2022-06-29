package com.sofkau.bugsmanagementbackend.usecases.projects.interfaces;

import com.sofkau.bugsmanagementbackend.dtos.ProjectDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteLeaderFunction {

    Mono<ProjectDTO> apply(String projectId, String leaderEmail);
}