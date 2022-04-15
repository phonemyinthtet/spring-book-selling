package com.example.bookrentalsellingproject.controller.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    @GetMapping("/adminOne")
    public String adminPage(HttpServletRequest request){
        if (request.isUserInRole("ROLE_USER")){
            return "user-layout/index";
        }
        return "admin-layout/admin-home";
    }





}
