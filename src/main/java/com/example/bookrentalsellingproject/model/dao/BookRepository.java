package com.example.bookrentalsellingproject.model.dao;

import com.example.bookrentalsellingproject.model.ds.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

}
