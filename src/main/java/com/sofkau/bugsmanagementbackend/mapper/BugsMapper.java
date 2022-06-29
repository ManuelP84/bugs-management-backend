package com.sofkau.bugsmanagementbackend.mapper;

import com.sofkau.bugsmanagementbackend.collections.Bugs;
import com.sofkau.bugsmanagementbackend.dtos.BugsDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BugsMapper {

    private final ModelMapper modelMapper;

    public Function<Bugs, BugsDTO> convertEntityToDto(){
        return bugs -> modelMapper.map(bugs, BugsDTO.class);
    }

    public Function<BugsDTO, Bugs> convertDtoToEntity(){
        return bugsDto -> modelMapper.map(bugsDto, Bugs.class);
    }
}
