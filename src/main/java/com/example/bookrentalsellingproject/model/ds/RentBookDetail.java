package com.example.bookrentalsellingproject.model.ds;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class RentBookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    @OneToOne
    private Book book;

}
