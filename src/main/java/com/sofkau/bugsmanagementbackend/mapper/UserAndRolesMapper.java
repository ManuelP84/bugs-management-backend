package com.sofkau.bugsmanagementbackend.mapper;

import com.sofkau.bugsmanagementbackend.collections.UserAndRoles;
import com.sofkau.bugsmanagementbackend.dtos.UserAndRolesDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserAndRolesMapper {

    private final ModelMapper modelMapper;

    public Function<UserAndRoles, UserAndRolesDTO> convertEntityToDto(){
        return user -> modelMapper.map(user, UserAndRolesDTO.class);
    }

    public Function<UserAndRolesDTO, UserAndRoles> convertDtoToEntity(){
        return userDto -> modelMapper.map(userDto, UserAndRoles.class);
    }
}
