package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.model.Cart;
import com.caseStudy.Khareedo.model.Orders;
import com.caseStudy.Khareedo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class CartCon {
    private CartService cartService;

    @Autowired
    public CartCon(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "/add")
    public List<Cart> addItemToCart(@RequestParam("id") Long productId, Principal principal) {
        return cartService.addToCart(principal, productId);
    }

    @PostMapping(value = "/remove")
    public List<Cart> removeItemFromCart(@RequestParam("id") Long productId, Principal principal) {
        return cartService.removeFromCart(principal, productId);
    }

    @PostMapping(value = "/reduce")
    public List<Cart> reduceItemFromCart(@RequestParam("id") Long productId, Principal principal) {
        return cartService.decreaseQuantity(principal, productId);
    }

    @GetMapping(value = "/get", produces = "application/json")
    @ResponseBody
    public List<Cart> getItemsInCart(Principal principal) {
        return cartService.getItemsInCart(principal);
    }

    @GetMapping(value = "/total", produces = "application/json")
    @ResponseBody
    public Double getTotal(Principal principal) {
        return cartService.cartTotal(principal);
    }

    @PostMapping(value = "/checkout")
    @ResponseBody
    public List<Orders> checkout(Principal principal) {
        return cartService.checkout(principal);
    }

    @GetMapping(value = "/orders", produces = "application/json")
    @ResponseBody
    public List<Orders> getOrders(Principal principal) {
        return cartService.getOrders(principal);
    }
}
