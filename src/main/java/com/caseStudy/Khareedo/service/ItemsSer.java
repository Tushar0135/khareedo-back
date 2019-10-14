package com.caseStudy.Khareedo.service;

import com.caseStudy.Khareedo.model.Items;
import com.caseStudy.Khareedo.repo.ItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsSer {
    private ItemsRepo itemsRepo;

    @Autowired
    public ItemsSer(ItemsRepo itemsRepo) {
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

    public Boolean clear() {
        itemsRepo.deleteAll();
        return true;
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

    public List<Items> getByPrice(Double lower, Double higher) {
        return itemsRepo.findAllByPriceBetween(lower, higher);
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

    public Items editItem(Items newItem, Long id) {
        Items oldItem = itemsRepo.findById(id).get();
        newItem.setProductId(oldItem.getProductId());
        newItem.setActive(oldItem.getActive());
        itemsRepo.saveAndFlush(newItem);
        return newItem;
    }
}
