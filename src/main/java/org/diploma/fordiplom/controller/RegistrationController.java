package org.diploma.fordiplom.controller;

import org.diploma.fordiplom.entity.UserEntity;
import org.diploma.fordiplom.repository.UserRepository;
import org.diploma.fordiplom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorConfirm", "Введенные пароли не совпадают");
            return "register";
        }
        if(userRepository.findByEmail(email).isPresent()){
            model.addAttribute("errorEmail", "Пользователь уже существует");
            return "register";
        }
        if(!userService.isValidPassword(password)){
            model.addAttribute("errorValidation", "Пароль не соответствует тербованиям");
            return "register";
        }
        try {
            UserEntity user = userService.createUser(email, password);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e);
        }
    }

}