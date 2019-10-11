package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/get1")
//
//    public List<Users> getallUsers() {
//
//        return userRepository.findAll();
//    }

    @GetMapping("/get1")
    public String getUser()
    {
        return "\"Successfully Login\"";
    }

    @PostMapping("/Signup")
    public Users createUser(@Valid @RequestBody Users user) {
        user.setRole("user");
        user.setActive(1);
        return userRepository.save(user);
    }

}
