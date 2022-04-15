package com.example.bookrentalsellingproject.controller.usercontroller;

import com.example.bookrentalsellingproject.model.dao.SellBookRepository;
import com.example.bookrentalsellingproject.model.dao.UserRepository;
import com.example.bookrentalsellingproject.model.ds.User;
import com.example.bookrentalsellingproject.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.attribute.UserPrincipal;

@Controller
public class BookBuyController {
    @Autowired
    private UserRepository user;
    @Autowired
    private CartDetailService cartDetailService;
    @Autowired
    private SellBookRepository sellBookRepository;

    //Already Work
    @GetMapping("/book-detail/{id}")
    public String showBookById(@PathVariable int id , Model model){

        model.addAttribute("book",sellBookRepository.findById(id).get());

        return "/user-layout/book-detail";
    }

    @GetMapping("/sell-book")
    public String sellBook(Model model){
        model.addAttribute("ratefive",sellBookRepository.findSellBookDetailByBookRate());
        model.addAttribute("ratefour",sellBookRepository.findSellBookDetailByBookRateFour());
        model.addAttribute("rateother",sellBookRepository.findSellBookDetailByBookOther());
        return "/user-layout/sell-book";
    }

}
