package org.diploma.fordiplom.controller;

import org.diploma.fordiplom.entity.ProjectEntity;
import org.diploma.fordiplom.entity.UserEntity;
import org.diploma.fordiplom.service.ProjectService;
import org.diploma.fordiplom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @PostMapping("/create_project")
    public String createProject(@RequestParam String name, @RequestParam String key_project, @RequestParam String description, Principal principal) {
        UserEntity currentUser = userService.getUserByEmail(principal.getName());

        if (currentUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        ProjectEntity project = new ProjectEntity();
        project.setName(name);
        project.setKey(key_project);
        project.setDescription(description);

        Set<UserEntity> users = new HashSet<>();
        users.add(currentUser);
        project.setUsers(users);

        projectService.createProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/projects")
    public String getUserProjects(Principal principal, Model model) {
        String currentUser = principal.getName();
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        List<ProjectEntity> projects = projectService.getProjectsByUserEmail(currentUser);
        model.addAttribute("projects", projects);
        return "projects";
    }
    @GetMapping("/projects/{id}")
    public String getProjectById(@PathVariable("id") Long id, Model model) {
        // Логика для получения проекта по id
        ProjectEntity project = projectService.findProjectById(id);
        model.addAttribute("project", project);
        return "project_page"; // Имя шаблона Thymeleaf для отображения страницы проекта
    }
}


