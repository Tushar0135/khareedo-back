package com.caseStudy.Khareedo.repo;

import com.caseStudy.Khareedo.model.Items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemsRepo extends JpaRepository<Items, Long> {
    Optional<Items> findByName(String name);

    List<Items> findAllByCategory(String category);

    List<Items> findAllByPriceBetween(Double lower, Double higher);

    List<Items> findAllByCategoryAndPriceBetween(String category, Double lower, Double higher);
}
