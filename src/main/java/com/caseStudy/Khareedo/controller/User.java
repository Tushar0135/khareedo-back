package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.service.UsersSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class User {
    private UsersSer usersSer;
//    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    public User(UsersSer usersSer) {
        this.usersSer = usersSer;
    }

    @GetMapping(path = "/profile")
    @ResponseBody
    public Users getProfile(Principal principal) {
        return usersSer.getUser(principal);
    }

    @PutMapping(path = "/update-profile")
    @ResponseBody
    public Users updateUser(@RequestBody Users users, Principal principal) {
        return usersSer.updateUser(users, principal);
    }

    @GetMapping("/users")
    @ResponseBody
    public List<Users> getAllUsers() {
        return usersSer.getUsers();
    }

    @GetMapping(path = "/login", produces = "application/json")
    @ResponseBody
    public String loginUser(Principal principal) {
//        return "true";
        return usersSer.login(principal);
    }

    @GetMapping(path = "/logout")
    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        return usersSer.logout(request, response);
    }

    @PostMapping("/sign-up")
    public Boolean registerUser(@RequestBody Users user) {
        return usersSer.addUser(user);
    }
}
