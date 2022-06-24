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
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.HashSet;

@SpringBootTest
public class GetProjectsUseCaseTest {

    private GetProjectsUseCase useCase;

    @Autowired
    private ProjectMapper mapper;

    @Mock
    IProjectRepository repository;

    @BeforeEach
    void setUp(){
        useCase = new GetProjectsUseCase(repository, mapper);
    }

    @Test
    void createProjectTest(){

        //Set a project 1
        Project project1 = new Project();
        project1.setId("g6f4536bv");
        project1.setProjectId(1234);
        project1.setName("Store management system");
        project1.setStartDate("06-06-2022");
        project1.setEndDate("05-07-2022");
        project1.setLeaderEmails(new HashSet<>());
        project1.setDeveloperEmails(new HashSet<>());
        project1.setDescription("Development from a store management system");

        //Set a project 2
        Project project2 = new Project();
        project2.setId("AFf4296bc");
        project2.setProjectId(1234);
        project2.setName("Blog web page");
        project2.setStartDate("06-07-2022");
        project2.setEndDate("02-09-2022");
        project2.setLeaderEmails(new HashSet<>());
        project2.setDeveloperEmails(new HashSet<>());
        project2.setDescription("Development from a blog web page");

        Mockito.when(repository.findAll()).thenReturn(Flux.just(project1, project2));

        Flux<ProjectDTO> fluxProjects = useCase.get();

        StepVerifier.create(fluxProjects)
                .expectNextCount(2)
                .verifyComplete();

        Mockito.verify(repository).findAll();
    }

}
