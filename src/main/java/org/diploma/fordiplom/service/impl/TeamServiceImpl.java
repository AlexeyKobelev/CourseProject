package org.diploma.fordiplom.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.diploma.fordiplom.entity.TeamEntity;
import org.diploma.fordiplom.repository.TeamRepository;
import org.diploma.fordiplom.repository.UserRepository;
import org.diploma.fordiplom.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;;

//    @Override
//    public UserEntity getUserByEmail(String email) {
//        return userRepository.findByEmail(email).orElse(null);
//    }

    @Override
    public TeamEntity createTeam(TeamEntity team) {
        return teamRepository.save(team);
    }

    @Override
    public TeamEntity getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Team not found"));
    }
    @Override
    public List<TeamEntity> getUserTeams(String email){return teamRepository.findByEmail(email);}

//    @Override
//    public TeamEntity updateTeam(TeamEntity team) {
//        return null;
//    }

}
