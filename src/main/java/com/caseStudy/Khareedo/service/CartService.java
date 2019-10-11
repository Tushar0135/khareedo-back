package com.caseStudy.Khareedo.service;

import com.caseStudy.Khareedo.model.Cart;
import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.model.items;
import com.caseStudy.Khareedo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    @Autowired
    Itemrepositry itemRepository;

    @Autowired
    CartRepo cartRepository;

    @Autowired
    UserRepository userRepository;

    public String addtocart(Long userid, Long product_id) {
        Optional<items> item = itemRepository.findById(product_id);
        Optional<Users> user = userRepository.findById((userid));
        if (cartRepository.findByUsersAndItems(user, item).isPresent()) {
            Cart cart= cartRepository.findByUsersAndItems(user,item).get();
            cart.setQuantity(cart.getQuantity()+1);
            cartRepository.save(cart);
            return "This item is already present in your cart";
        } else {
            Cart cart = new Cart();
            cart.setItems(item.get());
            cart.setUsers(user.get());
            cart.setQuantity(1);
            cartRepository.save(cart);
            return "Successfully added";
        }
    }


    public String removefromcart(Long getuserid, Long product_id) {
        Optional<items> item = itemRepository.findById(product_id);
        Optional<Users> user = userRepository.findById((getuserid));
        if (cartRepository.findByUsersAndItems(user, item).get().getQuantity() <= 1) {
            Cart car = cartRepository.findByUsersAndItems(user,item).get();
            cartRepository.delete(car);

        }
        else {
            Cart cart=  cartRepository.findByUsersAndItems(user,item).get();
            cart.setQuantity(cart.getQuantity()-1);
            cartRepository.save(cart);
        }
        return "successfully removed";
    }

//    public Cart addProduct(Long userid, Long product_id) {
//        Optional<items> item = itemRepository.findById(product_id);
//        Optional<Users> user = userRepository.findById(userid);
//
//        if (cartRepository.findByUsersAndItems(user, item).isPresent()) {
//            Cart cart =  cartRepository.findByUsersAndItems(user, item).get();
//            cart.setQuantity(cart.getQuantity() + 1);
//            cartRepository.save(cart);
//        } else {
//            Cart c = new Cart(item, user, 1);
//            cartRepository.save(c);
//        }
//        return cartRepository.findByUsersAndItems(user, item).get();
//    }



    public double checkout(Long getuserid, Principal principal) {
        return 0;
    }
    //
    public String clearcart(Long getuserid, Principal principal) {
        Optional<Users> users=userRepository.findById(getuserid);
        List<Cart> cartList =cartRepository.findByUsersAndItems_Active(users,1);
        for (Cart cart : cartList){
            cartRepository.deleteById(cart.getId());
        }
        return "cart Cleared";
    }

    public List<Cart> showcart(Long userid) {
        Optional<Users> users=userRepository.findById(userid);
        return cartRepository.findByUsersAndItems_Active(users,1);

    }
}

