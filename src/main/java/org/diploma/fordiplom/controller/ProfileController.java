package org.diploma.fordiplom.controller;

import org.diploma.fordiplom.entity.UserEntity;
import org.diploma.fordiplom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/searchProfile")
    public String getProfileByEmail(@RequestParam String email, Model model) {
        UserEntity user = userService.getUserByEmail(email);

        return "redirect:/profile/" + user.getId_user();
    }

    @GetMapping("/profile/{id}")
    public String getProfileById(@PathVariable Long id, Model model) {
        UserEntity user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "profile"; // Шаблон для отображения профиля
    }

    @GetMapping("/currentUser")
    public String getCurrentUser(Principal principal, Model model) {
        UserEntity user = userService.getCurrentUser(principal);
        model.addAttribute("user", user);
        return "profile";
    }
}
