package com.example.bookrentalsellingproject.controller.admincontroller;

import com.example.bookrentalsellingproject.model.dao.CartItemDetailRepository;
import com.example.bookrentalsellingproject.model.dao.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCartItemDetailController {
    @Autowired
    private CartItemDetailRepository cartItemDetailRepository;
    @GetMapping("/admin/customer-buy-list")
    public String showCartItemDetail(Model model){
        model.addAttribute("custoemrbuylist",cartItemDetailRepository.findAll());
        return "/admin-layout/admin-cart-item-detail-list";
    }


}
