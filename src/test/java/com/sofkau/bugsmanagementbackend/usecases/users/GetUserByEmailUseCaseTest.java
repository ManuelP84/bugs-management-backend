package com.sofkau.bugsmanagementbackend.usecases.users;

import com.sofkau.bugsmanagementbackend.collections.UserAndRoles;
import com.sofkau.bugsmanagementbackend.dtos.UserAndRolesDTO;
import com.sofkau.bugsmanagementbackend.repository.IUserAndRolesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GetUserByEmailUseCaseTest {

    @SpyBean
    private GetUserByEmailUseCase useCase;

    @MockBean
    private IUserAndRolesRepository reposiory;

    @Test
    void GetUserByEmailTest () {

        UserAndRolesDTO userDto1 = new UserAndRolesDTO();
        userDto1.setUid("001");
        userDto1.setUserImage("image");
        userDto1.setUserName("User name");
        userDto1.setUserEmail("email@email.com");
        userDto1.setUserToken("userToken123");
        userDto1.setUserRol("Admin");

        UserAndRolesDTO userDto2 = new UserAndRolesDTO();
        userDto2.setUid("001");
        userDto2.setUserImage("image");
        userDto2.setUserName("User name");
        userDto2.setUserEmail("email2@email.com.co");
        userDto2.setUserToken("userToken123");
        userDto2.setUserRol("Admin");

        UserAndRoles userEntity = new UserAndRoles();
        userEntity.setUid(userDto1.getUid());
        userEntity.setUserImage(userDto1.getUserImage());
        userEntity.setUserName(userDto1.getUserName());
        userEntity.setUserEmail(userDto1.getUserEmail());
        userEntity.setUserToken(userDto1.getUserToken());
        userEntity.setUserRol(userDto1.getUserRol());

        Mockito.when(reposiory.findByUserEmail(userDto1.getUserEmail())).thenReturn(Mono.just(userEntity));

        var resultMono = useCase.apply("email@email.com");

        StepVerifier
                .create(resultMono)
                .expectNext(userDto1)
                .verifyComplete();
    }
}