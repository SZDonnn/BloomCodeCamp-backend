package com.hcc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "Log in";
    }


    @GetMapping("/validate")
    public String validate() {
        return "<iframe width=\"100%\" height=\"100%\" frameborder=\"0\" " +
                "style = \" display:block; margin: 0 auto; \"" +
                "src=\"https://media.tenor.com/1UvPEeQ9WaMAAAAC/sweaty-speedrunner-epic-gamer.gif\">" +
                "</iframe>\"";
    }
}
