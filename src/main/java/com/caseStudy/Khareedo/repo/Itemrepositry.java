package com.caseStudy.Khareedo.repo;

import com.caseStudy.Khareedo.model.items;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Itemrepositry extends JpaRepository<items, Long> {

    @Override
    List<items> findAll();
    List<items> findAllByName(String Name);
    List<items> findAllByCategory(String category);

    Object findByCategory(String category);

    Object findByCategoryAndPriceBetween(String category, double p1, double p2);
}
