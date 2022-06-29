package com.sofkau.bugsmanagementbackend.usecases.projects;

import com.sofkau.bugsmanagementbackend.collections.Project;
import com.sofkau.bugsmanagementbackend.dtos.ProjectDTO;
import com.sofkau.bugsmanagementbackend.mapper.ProjectMapper;
import com.sofkau.bugsmanagementbackend.repository.IProjectRepository;
import com.sofkau.bugsmanagementbackend.usecases.projects.interfaces.DeleteLeaderFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeleteLeaderUseCase implements DeleteLeaderFunction {

    private final IProjectRepository repository;
    private final ProjectMapper projectMapper;

    @Override
    public Mono<ProjectDTO> apply(String projectId, String leaderEmail) {
        return repository
                .findById(projectId)
                .switchIfEmpty(Mono.error(new IllegalStateException("Project does not exist!")))
                .flatMap(project -> {
                    Project updatedProject = new Project();
                    Set<String> leaderEmails = project.getLeaderEmails();
                    if(leaderEmails.remove(leaderEmail)){
                        updatedProject.setId(project.getId());
                        updatedProject.setProjectId(project.getProjectId());
                        updatedProject.setName(project.getName());
                        updatedProject.setStartDate(project.getStartDate());
                        updatedProject.setEndDate(project.getEndDate());
                        updatedProject.setState(project.getState());
                        updatedProject.setLeaderEmails(leaderEmails);
                        updatedProject.setDeveloperEmails(project.getDeveloperEmails());
                        updatedProject.setDescription(project.getDescription());
                        return repository.save(updatedProject);
                    } else {
                        return Mono.error(new IllegalStateException("Email does not exist!"));
                    }
                }).map(project -> projectMapper.convertEntityToDto().apply(project));


    }
}
