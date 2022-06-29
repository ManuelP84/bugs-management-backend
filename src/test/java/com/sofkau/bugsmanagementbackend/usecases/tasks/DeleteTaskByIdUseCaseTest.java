package com.sofkau.bugsmanagementbackend.usecases.tasks;

import com.sofkau.bugsmanagementbackend.collections.EmailType;
import com.sofkau.bugsmanagementbackend.collections.LabelType;
import com.sofkau.bugsmanagementbackend.collections.Task;
import com.sofkau.bugsmanagementbackend.collections.UrlType;
import com.sofkau.bugsmanagementbackend.dtos.TaskDTO;
import com.sofkau.bugsmanagementbackend.repository.ITaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DeleteTaskByIdUseCaseTest {

    @SpyBean
    private DeleteTaskByIdUseCase useCase;

    @MockBean
    private ITaskRepository repository;

    @Test
    void deleteTaskByIdTest () {
        LabelType label1 = new LabelType();
        label1.setLabel("Task label 1");

        LabelType label2 = new LabelType();
        label2.setLabel("Task label 2");

        LabelType label3 = new LabelType();
        label3.setLabel("Task label 3");

        List<LabelType> labels = new ArrayList<>();
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);

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

        EmailType email1 = new EmailType();
        email1.setEmail("test@test1.com");

        EmailType email2 = new EmailType();
        email2.setEmail("test@test2.com");

        EmailType email3 = new EmailType();
        email3.setEmail("test@test3.com");

        List<EmailType> emails = new ArrayList<>();
        emails.add(email1);
        emails.add(email2);
        emails.add(email3);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId("001");
        taskDTO.setProjectId("001");
        taskDTO.setTaskId("001-1");
        taskDTO.setProjectName("Project name from task");
        taskDTO.setName("Task name");
        taskDTO.setDate("2022-06-28");
        taskDTO.setEndDate("2022-07-28");
        taskDTO.setLabels(labels);
        taskDTO.setDescription("Task description");
        taskDTO.setUrls(urls);
        taskDTO.setState("created");
        taskDTO.setDeveloperEmails(emails);

        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setProjectId(taskDTO.getProjectId());
        task.setTaskId(taskDTO.getTaskId());
        task.setProjectName(taskDTO.getProjectName());
        task.setName(taskDTO.getName());
        task.setDate(taskDTO.getDate());
        task.setEndDate(taskDTO.getEndDate());
        task.setLabels(taskDTO.getLabels());
        task.setDescription(taskDTO.getDescription());
        task.setUrls(taskDTO.getUrls());
        task.setState(taskDTO.getState());
        task.setDeveloperEmails(taskDTO.getDeveloperEmails());

        Mockito.when(repository.deleteById(task.getId())).thenReturn(Mono.empty());

        var result = useCase.apply("001");

        StepVerifier
                .create(result)
                .assertNext(response -> response.equals(null));
    }
}