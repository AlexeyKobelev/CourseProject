package org.diploma.fordiplom.service;

import org.diploma.fordiplom.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface UserService {
        public UserEntity createUser(String email, String password);
        public UserEntity updateUser(String email,UserEntity user);
        public UserEntity getUserByEmail(String email);
        public boolean isValidPassword(String password);
        public UserEntity findUserById(Long id);
        public UserEntity getCurrentUser(Principal principal);
}
