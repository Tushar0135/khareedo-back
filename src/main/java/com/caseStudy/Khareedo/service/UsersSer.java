package com.caseStudy.Khareedo.service;

import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.repo.UsersRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Service
public class UsersSer {
    private UsersRepo usersRepo;

    @Autowired
    public UsersSer(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public boolean addUser(Users user) {
        if (usersRepo.existsByEmail(user.getEmail())) {
            return false;
        }
        if (user.getRole() == null) {
            user.setRole("user");
        }
        user.setActive(1);
        System.out.println(user.getRole());
        usersRepo.saveAndFlush(user);
        return true;
    }

    public String login(Principal principal) {
        Users users = usersRepo.findByEmail(principal.getName()).get();
        return "\""+users.getRole()+"\"";
    }

    public Users getUser(Principal principal) {
        return usersRepo.findByEmail(principal.getName()).get();
    }

    public Users updateUser(Users newUser, Principal principal) {
        Users oldUser = usersRepo.findByEmail(principal.getName()).get();
        newUser.setUserId(oldUser.getUserId());
        newUser.setRole(oldUser.getRole());
        newUser.setActive(oldUser.getActive());
        usersRepo.saveAndFlush(newUser);
        return newUser;
    }

    public List<Users> editUser(Users newUser, Long id) {
        Users oldUser = usersRepo.findById(id).get();
        newUser.setUserId(oldUser.getUserId());
        newUser.setRole(oldUser.getRole());
        newUser.setActive(oldUser.getActive());
        usersRepo.saveAndFlush(newUser);
        return usersRepo.findAll();
    }

    public List<Users> getUsers() {
        return usersRepo.findAll();
    }

    public Users getUserById(Long id) {
        return usersRepo.findById(id).get();
    }

    public List<Users> deleteAllUsers() {
        usersRepo.deleteAll();
        return usersRepo.findAll();
    }

    public List<Users> deleteUser(Long id) {
        usersRepo.deleteById(id);
        return usersRepo.findAll();
    }

    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            request.getSession().invalidate();
        }
        return true;
    }

}
