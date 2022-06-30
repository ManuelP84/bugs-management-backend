package com.sofkau.bugsmanagementbackend.usecases.bugs;


import com.sofkau.bugsmanagementbackend.collections.Bugs;
import com.sofkau.bugsmanagementbackend.collections.UrlType;
import com.sofkau.bugsmanagementbackend.dtos.BugsDTO;
import com.sofkau.bugsmanagementbackend.repository.IBugsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GetAllBugsByProjectIdUseCaseTest {

    @SpyBean
    private GetAllBugsByProjectIdUseCase useCase;

    @MockBean
    private IBugsRepository repository;

    @Test
    void GetAllBugsByProjectIdTest () {
        UrlType url1 = new UrlType();
        url1.setUrl("http://url.test1.com.co");

        UrlType url2 = new UrlType();
        url2.setUrl("http://url.test2.com.co");

        UrlType url3 = new UrlType();
        url3.setUrl("http://url.test3.com.co");

        List<UrlType> urls = new ArrayList<>();
        urls.add(url1);
        urls.add(url2);
        urls.add(url3);

        BugsDTO bugDTO = new BugsDTO();
        bugDTO.setId("data for test");
        bugDTO.setBugId("data for test");
        bugDTO.setTitle("data for test");
        bugDTO.setDescription("data for test");
        bugDTO.setDate("data for test");
        bugDTO.setTesterEmail("data for test");
        bugDTO.setTaskId("001-1");
        bugDTO.setProjectId("11223344");
        bugDTO.setTesterNotes("data for test");
        bugDTO.setLifecycle("data for test");
        bugDTO.setUrls(urls);
        bugDTO.setScope("data for test");
        bugDTO.setPriority("data for test");
        bugDTO.setImportance("data for test");
        bugDTO.setState("data for test");
        bugDTO.setConclusions("data for test");
        bugDTO.setProblems("data for test");
        bugDTO.setReference("data for test");
        bugDTO.setEndDate("data for test");
        bugDTO.setDeveloperEmail("data for test");
        bugDTO.setDeveloperNotes("data for test");

        BugsDTO bugDTO2 = new BugsDTO();
        bugDTO2.setId("data for test");
        bugDTO2.setBugId("data for test");
        bugDTO2.setTitle("data for test");
        bugDTO2.setDescription("data for test");
        bugDTO2.setDate("data for test");
        bugDTO2.setTesterEmail("data for test");
        bugDTO2.setTaskId("001-2");
        bugDTO2.setProjectId("11223344");
        bugDTO2.setTesterNotes("data for test");
        bugDTO2.setLifecycle("data for test");
        bugDTO2.setUrls(urls);
        bugDTO2.setScope("data for test");
        bugDTO2.setPriority("data for test");
        bugDTO2.setImportance("data for test");
        bugDTO2.setState("data for test");
        bugDTO2.setConclusions("data for test");
        bugDTO2.setProblems("data for test");
        bugDTO2.setReference("data for test");
        bugDTO2.setEndDate("data for test");
        bugDTO2.setDeveloperEmail("data for test");
        bugDTO2.setDeveloperNotes("data for test");

        Bugs bug = new Bugs();
        bug.setId(bugDTO.getId());
        bug.setBugId(bugDTO.getBugId());
        bug.setTitle(bugDTO.getTitle());
        bug.setDescription(bugDTO.getDescription());
        bug.setDate(bugDTO.getDate());
        bug.setTesterEmail(bugDTO.getTesterEmail());
        bug.setTaskId(bugDTO.getTaskId());
        bug.setProjectId(bugDTO.getProjectId());
        bug.setTesterNotes(bugDTO.getTesterNotes());
        bug.setLifecycle(bugDTO.getLifecycle());
        bug.setUrls(bugDTO.getUrls());
        bug.setScope(bugDTO.getScope());
        bug.setPriority(bugDTO.getPriority());
        bug.setImportance(bugDTO.getImportance());
        bug.setState(bugDTO.getState());
        bug.setConclusions(bugDTO.getConclusions());
        bug.setProblems(bugDTO.getProblems());
        bug.setReference(bugDTO.getReference());
        bug.setEndDate(bugDTO.getEndDate());
        bug.setDeveloperEmail(bugDTO.getDeveloperEmail());
        bug.setDeveloperNotes(bugDTO.getDeveloperNotes());

        Bugs bug2 = new Bugs();
        bug2.setId(bugDTO2.getId());
        bug2.setBugId(bugDTO2.getBugId());
        bug2.setTitle(bugDTO2.getTitle());
        bug2.setDescription(bugDTO2.getDescription());
        bug2.setDate(bugDTO2.getDate());
        bug2.setTesterEmail(bugDTO2.getTesterEmail());
        bug2.setTaskId(bugDTO2.getTaskId());
        bug2.setProjectId(bugDTO2.getProjectId());
        bug2.setTesterNotes(bugDTO2.getTesterNotes());
        bug2.setLifecycle(bugDTO2.getLifecycle());
        bug2.setUrls(bugDTO2.getUrls());
        bug2.setScope(bugDTO2.getScope());
        bug2.setPriority(bugDTO2.getPriority());
        bug2.setImportance(bugDTO2.getImportance());
        bug2.setState(bugDTO2.getState());
        bug2.setConclusions(bugDTO2.getConclusions());
        bug2.setProblems(bugDTO2.getProblems());
        bug2.setReference(bugDTO2.getReference());
        bug2.setEndDate(bugDTO2.getEndDate());
        bug2.setDeveloperEmail(bugDTO2.getDeveloperEmail());
        bug2.setDeveloperNotes(bugDTO2.getDeveloperNotes());

        Mockito.when(repository.findAllByProjectId(bugDTO.getProjectId())).thenReturn(Flux.just(bug, bug2));

        var resultFlux = useCase.apply("11223344");

        StepVerifier
                .create(resultFlux)
                .expectNext(bugDTO)
                .expectNext(bugDTO2)
                .verifyComplete();
    }

}