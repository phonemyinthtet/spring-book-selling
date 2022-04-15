package com.example.bookrentalsellingproject.service;

import com.example.bookrentalsellingproject.model.dao.UserRepository;
import com.example.bookrentalsellingproject.model.ds.Role;
import com.example.bookrentalsellingproject.model.ds.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        user = new User(user.getUsername(),user.getPassword(),user.getEmail(), LocalDate.now(),"1");
        user.setRoles(Set.of(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsername(username);
        System.out.println(user.getUsername()+user.getPassword());
        if(user == null){
            throw new UsernameNotFoundException("Invalid username and password .");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

}
