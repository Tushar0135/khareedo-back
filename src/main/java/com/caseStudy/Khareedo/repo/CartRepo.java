package com.caseStudy.Khareedo.repo;

import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.model.Cart;
import com.caseStudy.Khareedo.model.items;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends CrudRepository<Cart,Long>
{
    List<Cart> findByUsersAndItems_Active(Optional<Users> users, int i);

    Optional<Cart> findByUsersAndItems(Optional<Users> user, Optional<items> item);
}
