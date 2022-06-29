package com.sofkau.bugsmanagementbackend.usecases.bugs;

import com.sofkau.bugsmanagementbackend.dtos.BugsDTO;
import com.sofkau.bugsmanagementbackend.mapper.BugsMapper;
import com.sofkau.bugsmanagementbackend.repository.IBugsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GetAllBugsByTaskIdUseCase implements Function<String, Flux<BugsDTO>> {

    private final IBugsRepository repository;
    private final BugsMapper mapper;

    @Override
    public Flux<BugsDTO> apply(String taskId){
        return repository
                .findAllByTaskId(taskId)
                .map(bugs -> mapper.convertEntityToDto().apply(bugs));
    }
}
