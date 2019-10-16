package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.model.Items;
import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.service.ItemsService;
import com.caseStudy.Khareedo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/admin")
public class AdminCon {
    private UsersService usersService;
    private ItemsService itemsService;

    @Autowired
    public AdminCon(UsersService usersService, ItemsService itemsService) {
        this.usersService = usersService;
        this.itemsService = itemsService;
    }

    @PostMapping("/add-user")
    public Boolean addUser(@RequestBody Users users) {
        return usersService.addUser(users);
    }

    @DeleteMapping("/remove-user")
    public List<Users> removeUser(@RequestParam("id") Long id) {
        return usersService.deleteUser(id);
    }

    @PutMapping("/edit-user")
    public List<Users> editUser(@RequestBody Users users, @RequestParam("id") Long id) {
        return usersService.editUser(users, id);
    }

    @GetMapping("/get-user")
    public Users getUserById(@RequestParam("id") Long id) {
        return usersService.getUserById(id);
    }

    @GetMapping("/get-users")
    public List<Users> getUsers() {
        return usersService.getUsers();
    }

    @PostMapping("/add-item")
    public Boolean addItem(@RequestBody Items items) {
        return itemsService.addItem(items);
    }

    @DeleteMapping("/remove-item")
    public List<Items> removeItem(@RequestParam("id") Long id) {
        return itemsService.deleteItem(id);
    }

    @PutMapping("/edit-item")
    public Items editItem(@RequestBody Items items, @RequestParam("id") Long id) {
        return itemsService.editItem(items, id);
    }

    @GetMapping("/get-item")
    public Items getItemById(@RequestParam("id") Long id) {
        return itemsService.getById(id);
    }

    @GetMapping("/get-items")
    public List<Items> getItems() {
        return itemsService.getItems();
    }
}
