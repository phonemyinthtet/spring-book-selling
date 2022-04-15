package com.example.bookrentalsellingproject.controller.admincontroller;

import com.example.bookrentalsellingproject.model.dao.CategoryRepository;
import com.example.bookrentalsellingproject.model.ds.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;

@Controller
public class AdminCategoryController {

    private final CategoryRepository categoryRepository;

    public AdminCategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/category")
    public String create(Model model){
        model.addAttribute("category",new Category());
        return "admin-layout/admin-category-create-form";
    }

    @PostMapping("/admin/category/create")
    public String process(@Valid Category category, BindingResult result){
        if (result.hasErrors()){
            return "admin-layout/admin-category-create-form";
        }
        categoryRepository.save(category);
        return "redirect:/admin/category/all";
    }
    @GetMapping("/admin/category/all")
    public String categoryList(Model model){
        model.addAttribute("categorys",categoryRepository.findAll());
        return "admin-layout/admin-category-list";
    }

    @GetMapping("/admin/category/delete-all")
    public String deleteAllCategory(){
        categoryRepository.deleteAll();
        return "redirect:/admin/category/all";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategoryById(@PathVariable int id){
        categoryRepository.deleteById(id);
       return "redirect:/admin/category/all";
    }
}
