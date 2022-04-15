package com.example.bookrentalsellingproject.controller.admincontroller;

import com.example.bookrentalsellingproject.model.dao.BookRepository;
import com.example.bookrentalsellingproject.model.dao.SellBookRepository;
import com.example.bookrentalsellingproject.model.ds.Book;
import com.example.bookrentalsellingproject.model.ds.SellBookDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminSellBookController {
    private final SellBookRepository sellBookRepository;
    private final BookRepository bookRepository;

    public AdminSellBookController(SellBookRepository sellBookRepository, BookRepository bookRepository) {
        this.sellBookRepository = sellBookRepository;
        this.bookRepository = bookRepository;
    }
    //Admin Crud
    @GetMapping("/admin/sellbook")
    public String create(Model model){
        model.addAttribute("sellbook",new SellBookDetail());
        model.addAttribute("books",bookRepository.findAll());
        return "admin-layout/admin-sellbook-create-form";
    }
    @PostMapping("/admin/sellbook/create")
    public String process(SellBookDetail sellbook, BindingResult result){
        sellBookRepository.save(sellbook);
        return "redirect:/admin/sellbook/all";
    }
    @GetMapping("/admin/sellbook/all")
    public String showList(Model model){

        model.addAttribute("books",sellBookRepository.findAll());
        return "admin-layout/admin-book-list";
    }




}


