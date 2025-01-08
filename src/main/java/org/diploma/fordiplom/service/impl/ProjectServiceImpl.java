package org.diploma.fordiplom.service.impl;

import org.diploma.fordiplom.entity.ProjectEntity;
import org.diploma.fordiplom.entity.UserEntity;
import org.diploma.fordiplom.repository.ProjectRepository;
import org.diploma.fordiplom.repository.UserRepository;
import org.diploma.fordiplom.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public ProjectEntity createProject(ProjectEntity project) {
        return projectRepository.save(project);
    }

    @Override
    public ProjectEntity findProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProjectEntity> getProjectsByUserEmail(String email) {
        return projectRepository.findByUserEmail(email);
    }

}
