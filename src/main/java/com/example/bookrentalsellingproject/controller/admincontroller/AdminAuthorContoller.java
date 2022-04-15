package com.example.bookrentalsellingproject.controller.admincontroller;

import com.example.bookrentalsellingproject.model.dao.AuthorRepository;
import com.example.bookrentalsellingproject.model.ds.Author;
import com.example.bookrentalsellingproject.model.ds.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminAuthorContoller {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/admin/author")
    public String create(Model model){
        model.addAttribute("author",new Author());
        return "admin-layout/admin-author-create-form";
    }

    @PostMapping("/admin/author/create")
    public String process(@Valid Author author, BindingResult result){
        if (result.hasErrors()){
            return "admin-layout/admin-author-create-form";
        }
        authorRepository.save(author);
        return "redirect:/admin/author/all";
    }
    @GetMapping("/admin/author/all")
    public String authorList(Model model){
        model.addAttribute("authors",authorRepository.findAll());
        return "admin-layout/admin-author-list";
    }

    @GetMapping("/admin/author/delete-all")
    public String deleteAllAuthor(){
        authorRepository.deleteAll();
        return "redirect:/admin/author/all";
    }

    @GetMapping("/admin/author/delete/{id}")
    public String deleteAuthorById(@PathVariable int id){
        authorRepository.deleteById(id);
        return "redirect:/admin/author/all";
    }


}
