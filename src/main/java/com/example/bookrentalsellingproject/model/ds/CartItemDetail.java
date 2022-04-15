package com.example.bookrentalsellingproject.model.ds;

import javax.persistence.*;

@Entity
public class CartItemDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private CartItem cartItem;

    @ManyToOne
    private SellBookDetail bookDetail;

    private int quantity;

    public CartItemDetail() {
    }

    public CartItemDetail(int quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public SellBookDetail getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(SellBookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
