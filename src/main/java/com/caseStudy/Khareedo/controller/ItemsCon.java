package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.model.Items;
import com.caseStudy.Khareedo.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ItemsCon {
    private ItemsService itemsService;

    @Autowired
    public ItemsCon(ItemsService itemsService) {
        this.itemsService = itemsService;
    }


//    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @PostMapping(path = "/add")
    public String addNewItem(@RequestBody Items items) {
        itemsService.addItem(items);
        return "Service hit!!";
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public Items getItemById(@RequestParam("id") Long id) {
        return itemsService.getById(id);
    }
    @GetMapping(path ="/homeItems")
    public List<Items> getHomeItems(){
        return itemsService.findAll();
    }
    @GetMapping(path = "/get-items/{category}", produces = "application/json")
    @ResponseBody
    public List<Items> getItemsByCategoryAndPrice(@PathVariable(value = "category") String category,
                                                  @RequestParam(value = "lower", required = false) Double lower,
                                                  @RequestParam(value = "higher", required = false) Double higher) {
        if (lower == null && higher == null) {
            return itemsService.getByCategory(category);
        }
        return itemsService.getByCategoryAndPrice(category, lower, higher);
    }

    @GetMapping(path = "/get-items/", produces = "application/json")
    @ResponseBody
    public Items getItemsByPrice(@RequestParam(value = "id") Long id) {
        return itemsService.getById(id);
    }
    @GetMapping(path = "/search")
    public Set<Items> getSearch(@RequestParam(value = "value") String value){
        return itemsService.getSearchedItems(value);
    }
}
