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

@SpringBootTest
public class CreateUserUseCaseTest {

    @SpyBean
    private CreateUserUseCase useCase;

    @MockBean
    private IUserAndRolesRepository repository;

    @Test
    void CreateUserTest (){
        UserAndRolesDTO userDto = new UserAndRolesDTO();
        userDto.setUid("001");
        userDto.setUserImage("image");
        userDto.setUserName("User name");
        userDto.setUserEmail("email@email.com");
        userDto.setUserToken("userToken123");
        userDto.setUserRol("Admin");

        UserAndRoles userEntity = new UserAndRoles();
        userEntity.setUid(userDto.getUid());
        userEntity.setUserImage(userDto.getUserImage());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setUserEmail(userDto.getUserEmail());
        userEntity.setUserToken(userDto.getUserToken());
        userEntity.setUserRol(userDto.getUserRol());

        Mockito.when(repository.save(userEntity)).thenReturn(Mono.just(userEntity));

        var resultMono = useCase.apply(userDto);

        StepVerifier
                .create(resultMono)
                .expectNext(userDto)
                .verifyComplete();
    }
}
