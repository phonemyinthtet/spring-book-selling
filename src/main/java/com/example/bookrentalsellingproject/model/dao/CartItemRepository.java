package com.example.bookrentalsellingproject.model.dao;

import com.example.bookrentalsellingproject.model.ds.Book;
import com.example.bookrentalsellingproject.model.ds.CartItem;
import com.example.bookrentalsellingproject.model.ds.SellBookDetail;
import com.example.bookrentalsellingproject.model.ds.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
        List<CartItem> findCartItemByUser(User user);

}
