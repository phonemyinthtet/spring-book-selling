package com.example.bookrentalsellingproject.controller.admincontroller;

import com.example.bookrentalsellingproject.model.dao.AuthorRepository;
import com.example.bookrentalsellingproject.model.dao.BookRepository;
import com.example.bookrentalsellingproject.model.dao.CategoryRepository;
import com.example.bookrentalsellingproject.model.ds.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminBookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public AdminBookController(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/book")
    public String create(Model model){
        model.addAttribute("categorys",categoryRepository.findAll());
        model.addAttribute("authors",authorRepository.findAll());
        model.addAttribute("publishers","");
        model.addAttribute("book",new Book());
        return "admin-layout/admin-book-create-form";
    }
    @PostMapping("/admin/book/create")
    public String process(Book book, BindingResult result){
        bookRepository.save(book);
        return "redirect:/admin/sellbook";
    }

}
