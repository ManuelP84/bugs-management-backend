package com.sofkau.bugsmanagementbackend.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "project")
public class Project {

    @Id
    private String id;

    private Integer projectId;

    private String name;

    private String startDate;

    private String endDate;

    private String state;

    private Set<String> leaderEmails;

    private Set<String> developerEmails;

    private String description;
}
