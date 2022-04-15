package com.example.bookrentalsellingproject.controller;

import com.example.bookrentalsellingproject.model.ds.User;
import com.example.bookrentalsellingproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "register";
        }
        return "redirect:/home";


    }

    @PostMapping("/registration")
    public String registerForm(@Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "register";
        }
        else {
            userService.save(user);
            return "redirect:/register?success";
        }
        }

}
