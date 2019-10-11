package com.caseStudy.Khareedo.repo;

import com.caseStudy.Khareedo.model.orderhistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepo extends JpaRepository <orderhistory , Long> {
}
