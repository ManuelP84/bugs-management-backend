package com.sofkau.bugsmanagementbackend.usecases.bugs;

import com.sofkau.bugsmanagementbackend.collections.Bugs;
import com.sofkau.bugsmanagementbackend.collections.UrlType;
import com.sofkau.bugsmanagementbackend.dtos.BugsDTO;
import com.sofkau.bugsmanagementbackend.mapper.BugsMapper;
import com.sofkau.bugsmanagementbackend.repository.IBugsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DeleteBugUseCaseTest {

    private DeleteBugUseCase useCase;

    @Autowired
    private BugsMapper mapper;

    @Mock
    private IBugsRepository repository;

    @BeforeEach
    void setUp(){
        useCase = new DeleteBugUseCase(repository);
    }

    @Test
    void deleteBugTest (){
        UrlType url1 = new UrlType();
        url1.setUrl("http://url.test1.com");
        url1.setFileName("file name 1");

        UrlType url2 = new UrlType();
        url2.setUrl("http://url.test2.com");
        url2.setFileName("file name 2");

        UrlType url3 = new UrlType();
        url3.setUrl("http://url.test3.com");
        url3.setFileName("file name 3");

        List<UrlType> urls = new ArrayList<>();
        urls.add(url1);
        urls.add(url2);
        urls.add(url3);

        Bugs bug = new Bugs();
        bug.setId("0258963147");
        bug.setBugId("1");
        bug.setTitle("Bug title");
        bug.setDescription("Bug description");
        bug.setDate("2022-06-29");
        bug.setTesterEmail("tester1@email.com");
        bug.setTaskId("001-1");
        bug.setTesterNotes("tester bug note");
        bug.setLifecycle("Bug lifecycle");
        bug.setUrls(urls);
        bug.setScope("Bug scope");
        bug.setPriority("Bug priority");
        bug.setImportance("Bug importance");
        bug.setState("Bug state");
        bug.setConclusions("Bug conclusion");
        bug.setProblems("Bug problem");
        bug.setReference("Bug reference");
        bug.setEndDate("2022-06-30");
        bug.setDeveloperEmail("developer@email.com");
        bug.setDeveloperNotes("Developer bug notes");

        Mockito.when(repository.findByBugId("1")).thenReturn(Mono.just(bug));

        Mockito.when(repository.deleteByBugId("1")).thenReturn(Mono.empty());

        Mono<Void> monoBug = useCase.apply("1");

        StepVerifier.create(monoBug)
                .expectComplete();

        Mockito.verify(repository).findByBugId("1");

    }



}
