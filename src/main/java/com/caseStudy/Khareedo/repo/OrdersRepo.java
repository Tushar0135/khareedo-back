package com.caseStudy.Khareedo.repo;

import com.caseStudy.Khareedo.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {
    List<Orders> findAllByUserIdOrderByDateDesc(Long id);
}
