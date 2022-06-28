package com.sofkau.bugsmanagementbackend.mapper;

import com.sofkau.bugsmanagementbackend.collections.Task;
import com.sofkau.bugsmanagementbackend.dtos.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final ModelMapper modelMapper;

    public Function<Task, TaskDTO> convertEntityToDto(){
        return task -> modelMapper.map(task, TaskDTO.class);
    }

    public Function<TaskDTO, Task> convertDtoToEntity(){
        return taskDto -> modelMapper.map(taskDto, Task.class);
    }
}
