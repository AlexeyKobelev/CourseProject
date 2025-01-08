package org.diploma.fordiplom.service.impl;
import jakarta.transaction.Transactional;
import org.diploma.fordiplom.entity.UserEntity;
import org.diploma.fordiplom.repository.UserRepository;
import org.diploma.fordiplom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import java.awt.desktop.UserSessionEvent;
import java.security.Principal;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUser(String email, String password) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(String email, UserEntity user) {
        UserEntity updateUser = getUserByEmail(email);
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        updateUser.setPosition(user.getPosition());
        updateUser.setOrganization(user.getOrganization());
        return userRepository.save(updateUser);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return password.matches(passwordPattern);
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public UserEntity getCurrentUser(Principal principal) {
        String user = principal.getName();
        return userRepository.findByEmail(user).orElseThrow(() -> new RuntimeException("User not found"));

    }

}
