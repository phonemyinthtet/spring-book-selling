package com.example.bookrentalsellingproject.model.dao;

import com.example.bookrentalsellingproject.model.ds.SellBookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellBookRepository extends JpaRepository<SellBookDetail,Integer> {
    @Query(value = "select s,b from SellBookDetail s inner join Book b on s.id = b.id where b.rate = 5  and s.quantity > 0")
    List<SellBookDetail> findSellBookDetailByBookRate();

    @Query(value = "select s,b from SellBookDetail s inner join Book b on s.id = b.id where b.rate = 4  and s.quantity > 0")
    List<SellBookDetail> findSellBookDetailByBookRateFour();

    @Query(value = "select s,b from SellBookDetail s inner join Book b on s.id = b.id where b.rate < 4 and s.quantity > 0")
    List<SellBookDetail> findSellBookDetailByBookOther();



}
