package com.sofkau.bugsmanagementbackend.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class ProjectDTO {

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
