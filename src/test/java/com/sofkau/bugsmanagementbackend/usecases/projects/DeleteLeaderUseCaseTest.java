package com.sofkau.bugsmanagementbackend.usecases.projects;

import com.sofkau.bugsmanagementbackend.collections.Project;
import com.sofkau.bugsmanagementbackend.dtos.ProjectDTO;
import com.sofkau.bugsmanagementbackend.mapper.ProjectMapper;
import com.sofkau.bugsmanagementbackend.repository.IProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
public class DeleteLeaderUseCaseTest {

    private DeleteLeaderUseCase useCase;

    @Autowired
    private ProjectMapper projectMappermapper;

    @Mock
    IProjectRepository repository;

    @BeforeEach
    void setUp(){
        useCase = new DeleteLeaderUseCase(repository, projectMappermapper);
    }

    @Test
    void deleteLeaderTest(){
        //Set a project
        Project project = new Project();
        project.setId("g6f4536bv");
        project.setProjectId(1234);
        project.setName("Store management system");
        project.setStartDate("06-06-2022");
        project.setEndDate("05-07-2022");
        project.setState("created");
        project.setLeaderEmails(new HashSet<String>(List.of("leader@gmail.com", "leader1@gmail.com")));
        project.setDeveloperEmails(new HashSet<>());
        project.setDescription("Development from a store management system");

        //Set an updated  project
        Project projectUpdated = new Project();
        projectUpdated.setId("g6f4536bv");
        projectUpdated.setProjectId(1234);
        projectUpdated.setName("Store management system!");
        projectUpdated.setStartDate("06-06-2022");
        projectUpdated.setEndDate("05-07-2022");
        projectUpdated.setState("created");
        projectUpdated.setLeaderEmails(new HashSet<>(List.of("leader@gmail.com")));
        projectUpdated.setDeveloperEmails(new HashSet<>());
        projectUpdated.setDescription("Development from a store management system");


        Mockito.when(repository.findById("g6f4536bv")).thenReturn(Mono.just(project));

        Mockito.when(repository.save(projectUpdated)).thenReturn(Mono.just(projectUpdated));

        Mono<ProjectDTO> monoProject = useCase.apply("g6f4536bv", "leader1@gmail.com");

        StepVerifier.create(monoProject)
                .assertNext(projectDTO -> Assertions.assertEquals(1, projectDTO.getLeaderEmails().size()))
                .expectNextCount(1)
                .expectComplete();

        Mockito.verify(repository).findById("g6f4536bv");

    }
}
