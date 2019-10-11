package com.caseStudy.Khareedo.repo;

import com.caseStudy.Khareedo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserRepo {
    @Autowired
    UserRepository userRepo;
    public void add(Users users)
    {
        System.out.println("Adding a value");
        userRepo.save(users);
    }
    public Optional<Users> getByEmail(String email)
    {
        System.out.println("Getting by email");
        return userRepo.findByEmail(email);
    }
    public Optional<Users> getById(Long id){
        return userRepo.findById(id);
    }
}
