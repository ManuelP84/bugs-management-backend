package com.sofkau.bugsmanagementbackend.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "task")
public class Task {

    @Id
    private String id;
    private String projectId;
    private String taskId;
    private String projectName;
    private String name;
    private String date;
    private String endDate;
    private List<LabelType> labels;
    private String description;
    private List<UrlType> urls;
    private String state;
    private List<EmailType> developerEmails;

}
