package com.example.bookrentalsellingproject.service;

import com.example.bookrentalsellingproject.model.ds.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);
}
