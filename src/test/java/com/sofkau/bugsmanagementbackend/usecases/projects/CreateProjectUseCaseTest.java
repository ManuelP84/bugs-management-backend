package com.sofkau.bugsmanagementbackend.usecases.projects;

import com.sofkau.bugsmanagementbackend.collections.Project;
import com.sofkau.bugsmanagementbackend.dtos.ProjectDTO;
import com.sofkau.bugsmanagementbackend.mapper.ProjectMapper;
import com.sofkau.bugsmanagementbackend.repository.IProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashSet;

@SpringBootTest
public class CreateProjectUseCaseTest {

    private CreateProjectUseCase useCase;

    @Autowired
    private ProjectMapper mapper;

    @Mock
    IProjectRepository repository;

    @BeforeEach
    void setUp(){
        useCase = new CreateProjectUseCase(repository, mapper);
    }

    @Test
    void createProjectTest(){
        //Set a projectDTO
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId("g6f4536bv");
        projectDTO.setProjectId(1234);
        projectDTO.setName("Store management system");
        projectDTO.setStartDate("06-06-2022");
        projectDTO.setEndDate("05-07-2022");
        projectDTO.setLeaderEmails(new HashSet<>());
        projectDTO.setDeveloperEmails(new HashSet<>());
        projectDTO.setDescription("Development from a store management system");

        //Set a project
        Project project = new Project();
        project.setId("g6f4536bv");
        project.setProjectId(1234);
        project.setName("Store management system");
        project.setStartDate("06-06-2022");
        project.setEndDate("05-07-2022");
        project.setLeaderEmails(new HashSet<>());
        project.setDeveloperEmails(new HashSet<>());
        project.setDescription("Development from a store management system");

        Mockito.when(repository.save(project)).thenReturn(Mono.just(project));

        Mono<ProjectDTO> monoProject = useCase.apply(mapper.convertEntityToDto().apply(project));

        StepVerifier.create(monoProject)
                .expectNextCount(1)
                .verifyComplete();
    }
}
