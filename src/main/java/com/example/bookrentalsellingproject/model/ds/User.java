package com.example.bookrentalsellingproject.model.ds;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username must not be empty")
    private String username;
    @NotEmpty(message = "password must not be empty")
    private String password;
    @NotEmpty(message = "email must not be empty")
    @Email(message = "this is not email format .")
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;
    private String status;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",referencedColumnName = "id"
            )
    )
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, String email, LocalDate localDate, String status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.localDate = localDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

