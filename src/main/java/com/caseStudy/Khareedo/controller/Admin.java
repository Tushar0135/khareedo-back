package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.model.Items;
import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.service.ItemsSer;
import com.caseStudy.Khareedo.service.UsersSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/admin")
public class Admin {
    private UsersSer usersSer;
    private ItemsSer itemsSer;

    @Autowired
    public Admin(UsersSer usersSer, ItemsSer itemsSer) {
        this.usersSer = usersSer;
        this.itemsSer = itemsSer;
    }

    @PostMapping("/add-user")
    public Boolean addUser(@RequestBody Users users) {
        return usersSer.addUser(users);
    }

    @DeleteMapping("/remove-user")
    public List<Users> removeUser(@RequestParam("id") Long id) {
        return usersSer.deleteUser(id);
    }

    @PutMapping("/edit-user")
    public List<Users> editUser(@RequestBody Users users, @RequestParam("id") Long id) {
        return usersSer.editUser(users, id);
    }

    @GetMapping("/get-user")
    public Users getUserById(@RequestParam("id") Long id) {
        return usersSer.getUserById(id);
    }

    @GetMapping("/get-users")
    public List<Users> getUsers() {
        return usersSer.getUsers();
    }

    @PostMapping("/add-item")
    public Boolean addItem(@RequestBody Items items) {
        return itemsSer.addItem(items);
    }

    @DeleteMapping("/remove-item")
    public List<Items> removeItem(@RequestParam("id") Long id) {
        return itemsSer.deleteItem(id);
    }

    @PutMapping("/edit-item")
    public Items editItem(@RequestBody Items items, @RequestParam("id") Long id) {
        return itemsSer.editItem(items, id);
    }

    @GetMapping("/get-item")
    public Items getItemById(@RequestParam("id") Long id) {
        return itemsSer.getById(id);
    }

    @GetMapping("/get-items")
    public List<Items> getItems() {
        return itemsSer.getItems();
    }
}
