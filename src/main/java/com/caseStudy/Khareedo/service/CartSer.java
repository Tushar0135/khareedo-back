package com.caseStudy.Khareedo.service;

import com.caseStudy.Khareedo.model.Cart;
import com.caseStudy.Khareedo.model.Items;
import com.caseStudy.Khareedo.model.Orders;
import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


import java.util.Date;

@Service
public class CartSer {
    private CartRepo cartRepository;
    private UsersRepo usersRepo;
    private ItemsRepo itemsRepo;
    private OrdersRepo ordersRepo;

    @Autowired
    public CartSer(CartRepo cartRepository, UsersRepo usersRepo, ItemsRepo itemsRepo,
                   OrdersRepo ordersRepo) {
        this.cartRepository = cartRepository;
        this.usersRepo = usersRepo;
        this.itemsRepo = itemsRepo;
        this.ordersRepo = ordersRepo;
    }

    public List<Cart> addToCart(Principal principal, Long productId) {
        Users user = usersRepo.findByEmail(principal.getName()).get();
        Items item = itemsRepo.findById(productId).get();
        if (cartRepository.findByUserAndItem(user, item).isPresent()) {
            Cart cart = cartRepository.findByUserAndItem(user, item).get();
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.saveAndFlush(cart);
        } else {
            Cart cart = new Cart();
            cart.setItem(item);
            cart.setUser(user);
            cart.setQuantity(1);
            cartRepository.saveAndFlush(cart);
        }
        return cartRepository.findAllByUser(user);
    }

    public List<Cart> removeFromCart(Principal principal, Long productId) {
        Users user = usersRepo.findByEmail(principal.getName()).get();
        Items item = itemsRepo.findById(productId).get();
        if (cartRepository.findByUserAndItem(user, item).isPresent()) {
            Cart cart = cartRepository.findByUserAndItem(user, item).get();
            cartRepository.delete(cart);
        }
        return cartRepository.findAllByUser(user);
    }

    public List<Cart> decreaseQuantity(Principal principal, Long productId) {
        Users user = usersRepo.findByEmail(principal.getName()).get();
        Items item = itemsRepo.findById(productId).get();
        if (cartRepository.findByUserAndItem(user, item).isPresent()) {
            Cart cart = cartRepository.findByUserAndItem(user, item).get();
            if (cart.getQuantity() == 1) {
                return cartRepository.findAllByUser(user);
            } else {
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepository.saveAndFlush(cart);
                return cartRepository.findAllByUser(user);
            }
        } else {
            return cartRepository.findAllByUser(user);
        }
    }

    public List<Cart> getItemsInCart(Principal principal) {
        Users users = usersRepo.findByEmail(principal.getName()).get();
        return cartRepository.findAllByUser(users);
    }

    public Double cartTotal(Principal principal) {
        Users users = usersRepo.findByEmail(principal.getName()).get();
        List<Cart> cartList = cartRepository.findAllByUser(users);
        double total = 0D;
        for (Cart cart : cartList) {
            total += cart.getQuantity() * cart.getItem().getPrice();
        }
        return total;
    }

    public List<Orders> checkout(Principal principal) {
        Users user = usersRepo.findByEmail(principal.getName()).get();
        List<Cart> cartList = cartRepository.findAllByUser(user);
        for (Cart cart : cartList) {
            Orders order = new Orders();
            order.setDate(new Date());
            order.setPrice(cart.getItem().getPrice());
            order.setProductName(cart.getItem().getName());
            order.setQuantity(cart.getQuantity());
            order.setUserId(cart.getUser().getUserId());
            ordersRepo.saveAndFlush(order);
            cartRepository.delete(cart);
        }
        return ordersRepo.findAllByUserIdOrderByDateDesc(user.getUserId());
    }

    public List<Orders> getOrders(Principal principal) {
        Users user = usersRepo.findByEmail(principal.getName()).get();
        return ordersRepo.findAllByUserIdOrderByDateDesc(user.getUserId());
    }
}
