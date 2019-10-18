package com.caseStudy.Khareedo.service;

import com.caseStudy.Khareedo.controller.ItemsCon;
import com.caseStudy.Khareedo.model.Items;
import com.caseStudy.Khareedo.repo.ItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemsService {
    private ItemsRepo itemsRepo;

    @Autowired
    public ItemsService(ItemsRepo itemsRepo) {
        this.itemsRepo = itemsRepo;
    }

    public Boolean addItem(Items items) {
        if (itemsRepo.findByName(items.getName()).isPresent()) {
            return false;
        }
        items.setActive(1);
        itemsRepo.saveAndFlush(items);
        return true;
    }

    public Set<Items> getSearchedItems(String value){
        ArrayList<Items> items=(ArrayList<Items>) itemsRepo.findAll();
        Set<Items> result=new HashSet<>();
        for (int i=0;i<items.size();i++){
            if(items.get(i).getName().toLowerCase().contains(value.toLowerCase())||
                    items.get(i).getCategory().contains(value.toLowerCase())||
                    items.get(i).getDetails().toLowerCase().contains(value.toLowerCase())||
                    items.get(i).getSubCategory().toLowerCase().contains(value.toLowerCase())){
                result.add(items.get(i));
            }
        }
            System.out.println("SearchResult "+result);
            return result;
    }
    public List<Items> getItems() {
        return itemsRepo.findAll();
    }

    public List<Items> getByCategory(String category) {
        return itemsRepo.findAllByCategory(category);
    }

    public List<Items> getByCategoryAndPrice(String category, Double lower, Double higher) {
        return itemsRepo.findAllByCategoryAndPriceBetween(category, lower, higher);
    }


    public Items getById(Long id) {
        if (itemsRepo.findById(id).isPresent()) {
            return itemsRepo.findById(id).get();
        }
        return null;
    }
    public List<Items> deleteItem(Long id) {
        itemsRepo.deleteById(id);
        return itemsRepo.findAll();
    }
    public List<Items> findAll(){
        return itemsRepo.findAll();
    }

    public Items editItem(Items newItem, Long id) {
        Items oldItem = itemsRepo.findById(id).get();
        newItem.setProductId(oldItem.getProductId());
        newItem.setActive(oldItem.getActive());
        itemsRepo.saveAndFlush(newItem);
        return newItem;
    }

}
