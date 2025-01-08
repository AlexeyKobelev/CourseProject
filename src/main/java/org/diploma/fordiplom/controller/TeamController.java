package org.diploma.fordiplom.controller;

import org.diploma.fordiplom.entity.TeamEntity;
import org.diploma.fordiplom.entity.UserEntity;
import org.diploma.fordiplom.service.TeamService;
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
import java.util.Optional;
import java.util.Set;

@Controller
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @PostMapping("/create_team")
    public String createTeam(
            @RequestParam String team_name,
            @RequestParam String emails,
            Principal principal) {
        TeamEntity newTeam = new TeamEntity();
        newTeam.setTeam_name(team_name);
        Set<UserEntity> users = new HashSet<>();

        UserEntity currentUser = userService.getCurrentUser(principal);
        users.add(currentUser);

        String[] emailsList = emails.split(",");
        for (String email : emailsList) {
            email = email.trim();
            try {
                UserEntity user = userService.getUserByEmail(email);
                users.add(user);
            } catch (RuntimeException e) {
                System.out.println("User not found: " + email);
            }
        }
        newTeam.setEmails(users);
        teamService.createTeam(newTeam);

        return "redirect:/team/" + newTeam.getId_team();
    }

    @GetMapping("/teams")
    public String getUserTeams(Principal principal, Model model) {
        String currentUser = principal.getName();
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        List<TeamEntity> teams = teamService.getUserTeams(currentUser);
        model.addAttribute("user", currentUser);
        model.addAttribute("teams", teams);
        return "teams";
    }
    @GetMapping("/team/{id}")
    public String getTeam(@PathVariable Long id, Model model) {
        TeamEntity team = teamService.getTeamById(id);
        model.addAttribute("team", team);
        return "TeamPage";
    }
}
