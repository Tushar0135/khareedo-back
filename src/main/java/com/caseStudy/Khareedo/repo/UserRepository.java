package com.caseStudy.Khareedo.repo;

import com.caseStudy.Khareedo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository  extends JpaRepository<Users,Long> {
    @Override
    List<Users> findAll();
    Optional<Users> findByEmail(String email);
}
