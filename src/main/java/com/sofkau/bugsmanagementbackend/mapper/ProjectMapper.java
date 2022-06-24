package com.sofkau.bugsmanagementbackend.mapper;

import com.sofkau.bugsmanagementbackend.collections.Project;
import com.sofkau.bugsmanagementbackend.dtos.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final ModelMapper modelMapper;

    public Function<Project, ProjectDTO> convertEntityToDto(){
        return project -> modelMapper.map(project, ProjectDTO.class);
    }

    public Function<ProjectDTO, Project> convertDtoToEntity(){
        return projectDTO -> modelMapper.map(projectDTO, Project.class);
    }
}
