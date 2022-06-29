package com.sofkau.bugsmanagementbackend.usecases.bugs;

import com.sofkau.bugsmanagementbackend.dtos.BugsDTO;
import com.sofkau.bugsmanagementbackend.mapper.BugsMapper;
import com.sofkau.bugsmanagementbackend.repository.IBugsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CreateBugUseCase implements Function<BugsDTO, Mono<BugsDTO>> {

    private final IBugsRepository repository;
    private final BugsMapper mapper;

    @Override
    public Mono<BugsDTO> apply(BugsDTO bugDTO){
        return repository
                .save(mapper.convertDtoToEntity().apply(bugDTO))
                .map(bugs -> mapper.convertEntityToDto().apply(bugs));
    }
}
