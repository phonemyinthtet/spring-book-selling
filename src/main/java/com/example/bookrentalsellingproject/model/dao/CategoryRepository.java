package com.example.bookrentalsellingproject.model.dao;

import com.example.bookrentalsellingproject.model.ds.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
