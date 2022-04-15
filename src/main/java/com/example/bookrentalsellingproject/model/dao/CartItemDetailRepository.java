package com.example.bookrentalsellingproject.model.dao;

import com.example.bookrentalsellingproject.model.ds.CartItem;
import com.example.bookrentalsellingproject.model.ds.CartItemDetail;
import com.example.bookrentalsellingproject.model.ds.SellBookDetail;
import com.example.bookrentalsellingproject.model.ds.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemDetailRepository extends JpaRepository<CartItemDetail,Integer> {

    @Query("select count(c) from CartItemDetail c")
    Integer findByBookDetailContains();

    List<CartItemDetail>  findByCartItemId(int id);



}
