package com.example.bookrentalsellingproject.controller.usercontroller;

import com.example.bookrentalsellingproject.model.dao.CartItemDetailRepository;
import com.example.bookrentalsellingproject.model.dao.CartItemRepository;
import com.example.bookrentalsellingproject.model.dao.SellBookRepository;
import com.example.bookrentalsellingproject.model.dao.UserRepository;
import com.example.bookrentalsellingproject.model.ds.CartItem;
import com.example.bookrentalsellingproject.model.ds.CartItemDetail;
import com.example.bookrentalsellingproject.model.ds.SellBookDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class CartController{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartItemDetailRepository cartItemDetailRepository;
    @Autowired
    private SellBookRepository sellBookRepository;

    private List<SellBookDetail> books = new ArrayList<>();
    private Map<Integer,List<SellBookDetail>> linkUserAndBooks = new HashMap<>();
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable int id){
          //User Id and Map Key Set ID same phit nay yin links
        linkUserAndBooks.put(userRepository.findUserByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getId(),new ArrayList<SellBookDetail>());

            boolean isExisted = false;
            for (SellBookDetail bookDetail : books){
                //list id == id
                if (bookDetail.getId() == id){
                    isExisted = true;
                }
            }
            if (!isExisted){
                for (Integer integer : linkUserAndBooks.keySet()) {
                    if(integer == userRepository.findUserByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getId() ){

                        linkUserAndBooks.get(integer).add(sellBookRepository.findById(id).get());
                    }
                }
                /*linkUserAndBooks.get(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).add(sellBookRepository.findById(id).get());*/


            }
            return "redirect:/book-detail/" + id;


        }
    @GetMapping("/cart")
    public String showCartItemList(Model model){
        //books
        model.addAttribute("cartitems",linkUserAndBooks.get(userRepository.findUserByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getId()));
        return "/user-layout/shopping-cart";
    }
    @GetMapping("/cart/checkoutview")
    public String checkoutView(Model model){

        model.addAttribute("books",linkUserAndBooks.get(userRepository.findUserByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getId()));
        model.addAttribute("cartItemDetail",new CartItemDetail());
        return "user-layout/checkout";
    }
    private double sum;
    int quantity ;
    @PostMapping("/cart/calculate")
    public String calculate(CartItemDetail cartQuantity , BindingResult result){
        var cartItem = new CartItem(LocalDate.now());
        cartItem.setUser(userRepository.findUserByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        cartItemRepository.save(cartItem);
        double sum = 0;
        int i = 0;
        for (SellBookDetail sellBookDetail:linkUserAndBooks.get(userRepository.findUserByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getId())){
                quantity=cartQuantity.getQuantity();
               if (quantity > sellBookRepository.findById(sellBookDetail.getId()).get().getQuantity() && sellBookRepository.findById(sellBookDetail.getId()).get().getQuantity() == 0 || result.hasErrors()){
                   result.rejectValue("quantity","error.quantity","Not Enought in inventory");

                   return "user-layout/checkout";
               }else {
                   cartQuantity = new CartItemDetail();
                   sum+=sellBookDetail.getPrice() * quantity;
                   cartQuantity.setCartItem(cartItem);
                   cartQuantity.setBookDetail(sellBookDetail);

                   cartQuantity.setQuantity(quantity);
                   sellBookRepository.findById(sellBookDetail.getId()).get().setQuantity(sellBookDetail.getQuantity()-quantity);
                   i++;
                   cartItemDetailRepository.save(cartQuantity);
               }


        }
        this.sum = sum;
        return "redirect:/balance/"+cartItem.getId();
    }

    @GetMapping("/balance/{id}")
    public String viewBalance(@PathVariable int id,Model model){
        model.addAttribute("total",sum);
        model.addAttribute("carts",cartItemDetailRepository.findByCartItemId(id));
        linkUserAndBooks.remove(userRepository.findUserByUsername(findUserDetail().getUsername()).getId());
        return "user-layout/balance";
    }
    private User findUserDetail(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
