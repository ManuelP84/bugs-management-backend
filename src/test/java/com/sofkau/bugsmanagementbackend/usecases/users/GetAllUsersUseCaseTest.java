package com.sofkau.bugsmanagementbackend.usecases.users;

import com.sofkau.bugsmanagementbackend.collections.UserAndRoles;
import com.sofkau.bugsmanagementbackend.dtos.UserAndRolesDTO;
import com.sofkau.bugsmanagementbackend.repository.IUserAndRolesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GetAllUsersUseCaseTest {

    @SpyBean
    private GetAllUsersUseCase useCase;

    @MockBean
    private IUserAndRolesRepository repository;

    @Test
    void getAllUsersTest (){

        UserAndRolesDTO user1Dto = new UserAndRolesDTO();
        user1Dto.setUid("12345");
        user1Dto.setUserImage("User 1 image");
        user1Dto.setUserName("User 1 name");
        user1Dto.setUserEmail("user1@email1.com");
        user1Dto.setUserToken("user1 token");
        user1Dto.setUserRol("user 1 rol");

        UserAndRolesDTO user2Dto = new UserAndRolesDTO();
        user2Dto.setUid("12345");
        user2Dto.setUserImage("User 2 image");
        user2Dto.setUserName("User 2 name");
        user2Dto.setUserEmail("user2@email2.com");
        user2Dto.setUserToken("user2 token");
        user2Dto.setUserRol("user 2 rol");

        UserAndRoles user1 = new UserAndRoles();
        user1.setUid(user1Dto.getUid());
        user1.setUserImage(user1Dto.getUserImage());
        user1.setUserName(user1Dto.getUserName());
        user1.setUserEmail(user1Dto.getUserEmail());
        user1.setUserToken(user1Dto.getUserToken());
        user1.setUserRol(user1Dto.getUserRol());

        UserAndRoles user2 = new UserAndRoles();
        user2.setUid(user2Dto.getUid());
        user2.setUserImage(user2Dto.getUserImage());
        user2.setUserName(user2Dto.getUserName());
        user2.setUserEmail(user2Dto.getUserEmail());
        user2.setUserToken(user2Dto.getUserToken());
        user2.setUserRol(user2Dto.getUserRol());

        Mockito.when(repository.findAll()).thenReturn(Flux.just(user1, user2));

        Flux<UserAndRolesDTO> entityResponse = useCase.get();

        StepVerifier
                .create(entityResponse)
                .expectNext(user1Dto)
                .expectNext(user2Dto)
                .verifyComplete();
    }
}