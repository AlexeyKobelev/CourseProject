package org.diploma.fordiplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {
    @GetMapping("/")
    public String index() {
        return "hello";
    }

    @GetMapping("/register")
    public String registrtion(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/TeamPage")
    public String teamPage(){
        return "TeamPage";
    }
    @GetMapping("/project_page")
    public String projectPage(){
        return "project_page";
    }
}
