package com.example.bookrentalsellingproject.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/loginForm")
    public String login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/default";

    }
    @GetMapping("/default")
    public String defaultUrl(HttpServletRequest request){
        if (request.isUserInRole("ROLE_ADMIN")){
            return "redirect:/adminOne";
        }
        return "redirect:/home";
    }

}
