package org.diploma.fordiplom.service;

import org.diploma.fordiplom.entity.ProjectEntity;
import org.diploma.fordiplom.entity.UserEntity;
import org.diploma.fordiplom.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    public ProjectEntity createProject(ProjectEntity project);
    public ProjectEntity findProjectById(Long id);

    public List<ProjectEntity> getProjectsByUserEmail(String email);
}
