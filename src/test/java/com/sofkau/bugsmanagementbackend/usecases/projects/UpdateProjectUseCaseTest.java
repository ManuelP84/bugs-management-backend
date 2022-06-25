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
public class UpdateProjectUseCaseTest {

    private UpdateProjectUseCase useCase;

    @Autowired
    private ProjectMapper mapper;

    @Mock
    IProjectRepository repository;

    @BeforeEach
    void setUp(){
        useCase = new UpdateProjectUseCase(repository, mapper);
    }

    @Test
    void updateProjectTest(){

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

        //Set an updated  project
        Project projectUpdated = new Project();
        projectUpdated.setId("g6f4536bv");
        projectUpdated.setProjectId(1234);
        projectUpdated.setName("Store management system updated!");
        projectUpdated.setStartDate("06-06-2022");
        projectUpdated.setEndDate("05-07-2022");
        projectUpdated.setLeaderEmails(new HashSet<>());
        projectUpdated.setDeveloperEmails(new HashSet<>());
        projectUpdated.setDescription("Development from a store management system");

        Mockito.when(repository.findById("g6f4536bv")).thenReturn(Mono.just(project));

        Mockito.when(repository.save(projectUpdated)).thenReturn(Mono.just(projectUpdated));

        Mono<ProjectDTO> monoProject = useCase.apply(mapper.convertEntityToDto().apply(projectUpdated));

        StepVerifier.create(monoProject)
                .expectNextMatches(projectDTO -> projectDTO.getName().equals("Store management system updated!"))
                .verifyComplete();
    }
}
