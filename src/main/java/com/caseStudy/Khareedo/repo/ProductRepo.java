package com.caseStudy.Khareedo.repo;

import com.caseStudy.Khareedo.model.items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;


@Component
public class ProductRepo
{
    @Autowired
    Itemrepositry itemRepo;

    public boolean addProduct(items item)
    {
        try{
            System.out.println("adding a product");
            itemRepo.save(item);
            System.out.println("product added");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    public ArrayList<items> getAllItems()
    {
        return (ArrayList<items>) itemRepo.findAll();
    }
    public Optional<items> getFields(Long id)
    {
        return itemRepo.findById(id);
    }
    public ArrayList<items> getByCategory(String category)
    {
        return (ArrayList<items>) itemRepo.findByCategory(category);
    }
    public ArrayList<items> getByCategoryAndPriceBetween(String category,double p1,double p2)
    {
        return (ArrayList<items>) itemRepo.findByCategoryAndPriceBetween(category,p1,p2);
    }
    public Optional<items> getById(Long id){
        return itemRepo.findById(id);
    }
}
