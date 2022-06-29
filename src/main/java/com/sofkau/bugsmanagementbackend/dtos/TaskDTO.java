package com.sofkau.bugsmanagementbackend.dtos;

import com.sofkau.bugsmanagementbackend.collections.EmailType;
import com.sofkau.bugsmanagementbackend.collections.LabelType;
import com.sofkau.bugsmanagementbackend.collections.UrlType;
import lombok.Data;

import java.util.List;
@Data
public class TaskDTO {

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
