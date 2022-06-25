package com.sofkau.bugsmanagementbackend.dtos;

import lombok.Data;

@Data
public class UserAndRolesDTO {

    private String uid;
    private String userImage;
    private String userName;
    private String userEmail;
    private String userToken;
    private String userRol;
}
