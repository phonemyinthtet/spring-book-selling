package com.example.bookrentalsellingproject.model.dao;

import com.example.bookrentalsellingproject.model.ds.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);

}
