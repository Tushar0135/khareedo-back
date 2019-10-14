package com.caseStudy.Khareedo.repo;

import com.caseStudy.Khareedo.model.Users;
import com.caseStudy.Khareedo.model.Cart;
import com.caseStudy.Khareedo.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(Users users);

    Optional<Cart> findByUserAndItem(Users user, Items items);
}
