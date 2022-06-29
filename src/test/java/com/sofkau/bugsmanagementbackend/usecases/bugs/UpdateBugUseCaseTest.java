package com.sofkau.bugsmanagementbackend.usecases.bugs;

import com.sofkau.bugsmanagementbackend.collections.Bugs;
import com.sofkau.bugsmanagementbackend.collections.UrlType;
import com.sofkau.bugsmanagementbackend.dtos.BugsDTO;
import com.sofkau.bugsmanagementbackend.mapper.BugsMapper;
import com.sofkau.bugsmanagementbackend.repository.IBugsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class UpdateBugUseCaseTest {

    @SpyBean
    private UpdateBugUseCase useCase;

    @MockBean
    private IBugsRepository repository;

    @Test
    void UpdateBugTest (){
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

        BugsDTO bugDTO = new BugsDTO();
        bugDTO.setId("0258963147");
        bugDTO.setBugId("1");
        bugDTO.setTitle("Bug title");
        bugDTO.setDescription("Bug description");
        bugDTO.setDate("2022-06-29");
        bugDTO.setTesterEmail("tester1@email.com");
        bugDTO.setTaskId("001-1");
        bugDTO.setTesterNotes("tester bug note");
        bugDTO.setLifecycle("Bug lifecycle");
        bugDTO.setUrls(urls);
        bugDTO.setScope("Bug scope");
        bugDTO.setPriority("Bug priority");
        bugDTO.setImportance("Bug importance");
        bugDTO.setState("Bug state");
        bugDTO.setConclusions("Bug conclusion");
        bugDTO.setProblems("Bug problem");
        bugDTO.setReference("Bug reference");
        bugDTO.setEndDate("2022-06-30");
        bugDTO.setDeveloperEmail("developer@email.com");
        bugDTO.setDeveloperNotes("Developer bug notes");

        Bugs bug = new Bugs();
        bug.setId(bugDTO.getId());
        bug.setBugId(bugDTO.getBugId());
        bug.setTitle(bugDTO.getTitle());
        bug.setDescription(bugDTO.getDescription());
        bug.setDate(bugDTO.getDate());
        bug.setTesterEmail(bugDTO.getTesterEmail());
        bug.setTaskId(bugDTO.getTaskId());
        bug.setTesterNotes(bugDTO.getTesterNotes());
        bug.setLifecycle(bugDTO.getLifecycle());
        bug.setUrls(urls);
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

        Mockito.when(repository.save(bug)).thenReturn(Mono.just(bug));

        var resultMono = useCase.apply(bugDTO);

        StepVerifier
                .create(resultMono)
                .expectNext(bugDTO)
                .verifyComplete();
    }

}