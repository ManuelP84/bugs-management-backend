package com.sofkau.bugsmanagementbackend.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserAndRoles {

    @Id
    private String uid;
    private String userImage;
    private String userName;
    private String userEmail;
    private String userToken;
    private String userRol;











}
