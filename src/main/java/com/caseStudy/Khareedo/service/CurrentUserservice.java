package com.caseStudy.Khareedo.service;

import com.caseStudy.Khareedo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CurrentUserservice {
    @Autowired
    UserRepository userRepository;


    public Long getUserid(Principal principal) {
        String email =principal.getName();
        Long id= userRepository.findByEmail(email).get().getId();
        return id;
    }
}
