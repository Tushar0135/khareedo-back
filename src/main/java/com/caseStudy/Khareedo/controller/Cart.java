package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.model.Orders;
import com.caseStudy.Khareedo.service.CartSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class Cart {
    private CartSer cartSer;

    @Autowired
    public Cart(CartSer cartSer) {
        this.cartSer = cartSer;
    }

    @PostMapping(value = "/add")
    public List<Cart> addItemToCart(@RequestParam("id") Long productId, Principal principal) {
        return cartSer.addToCart(principal, productId);
    }

    @PostMapping(value = "/remove")
    public List<Cart> removeItemFromCart(@RequestParam("id") Long productId, Principal principal) {
        return cartSer.removeFromCart(principal, productId);
    }

    @PostMapping(value = "/reduce")
    public List<Cart> reduceItemFromCart(@RequestParam("id") Long productId, Principal principal) {
        return cartSer.decreaseQuantity(principal, productId);
    }

    @GetMapping(value = "/get", produces = "application/json")
    @ResponseBody
    public List<Cart> getItemsInCart(Principal principal) {
        return cartSer.getItemsInCart(principal);
    }

    @GetMapping(value = "/total", produces = "application/json")
    @ResponseBody
    public Double getTotal(Principal principal) {
        return cartSer.cartTotal(principal);
    }

    @PostMapping(value = "/checkout")
    @ResponseBody
    public List<Orders> checkout(Principal principal) {
        return cartSer.checkout(principal);
    }

    @GetMapping(value = "/orders", produces = "application/json")
    @ResponseBody
    public List<Orders> getOrders(Principal principal) {
        return cartSer.getOrders(principal);
    }
}
