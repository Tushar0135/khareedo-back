package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserCon {
    private UsersService usersService;

    @Autowired
    public UserCon(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(path = "/profile")
    @ResponseBody
    public Users getProfile(Principal principal) {
        return usersService.getUser(principal);
    }

    @PutMapping(path = "/update-profile")
    @ResponseBody
    public Users updateUser(@RequestBody Users users, Principal principal) {
        return usersService.updateUser(users, principal);
    }

    @GetMapping("/users")
    @ResponseBody
    public List<Users> getAllUsers() {
        return usersService.getUsers();
    }

    @GetMapping(path = "/login", produces = "application/json")
    @ResponseBody
    public String loginUser(Principal principal) {
        return usersService.login(principal);
    }

    @GetMapping(path = "/logout")
    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        return usersService.logout(request, response);
    }

    @PostMapping("/sign-up")
    public Boolean registerUser(@RequestBody Users user) {
        return usersService.addUser(user);
    }
}
