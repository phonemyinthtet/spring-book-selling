package com.example.bookrentalsellingproject.controller.usercontroller;

import com.example.bookrentalsellingproject.model.dao.SellBookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class IndexController {

    private final SellBookRepository sellBookRepository;

    public IndexController(SellBookRepository sellBookRepository) {
        this.sellBookRepository = sellBookRepository;
    }

    @GetMapping(value = {"/home","/"})
    public String index(Model model){

        model.addAttribute("sellbooks",sellBookRepository.findSellBookDetailByBookRate());

        return "user-layout/index";
    }


}
