package com.sofkau.bugsmanagementbackend.dtos;

import com.sofkau.bugsmanagementbackend.collections.UrlType;
import lombok.Data;

import java.util.List;
@Data
public class BugsDTO {

    private String id;
    private String bugId;
    private String title;
    private String description;
    private String date;
    private String testerEmail;
    private String taskId;
    private String testerNotes;
    private String lifecycle;
    private List<UrlType> urls;
    private String scope;
    private String priority;
    private String importance;
    private String state;
    private String conclusions;
    private String problems;
    private String reference;
    private String endDate;
    private String developerEmail;
    private String developerNotes;
}
