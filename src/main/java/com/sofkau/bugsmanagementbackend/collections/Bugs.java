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
@Document(collection = "bugs")
public class Bugs {

    @Id
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
