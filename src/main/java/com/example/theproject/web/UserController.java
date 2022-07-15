package com.example.theproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/users/login")
    public String login(){
        return "login";
    }

//    @PostMapping("/users/login")
//    public String loginConfirm() {
//
//        return "index";
//    }
}
