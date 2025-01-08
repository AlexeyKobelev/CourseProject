package org.diploma.fordiplom.service;

import org.diploma.fordiplom.entity.TeamEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamService {
    //List<UserEntity> getAllUsers();
    public List<TeamEntity> getUserTeams(String email);
    public TeamEntity createTeam(TeamEntity team);
    TeamEntity getTeamById(Long id);
}
