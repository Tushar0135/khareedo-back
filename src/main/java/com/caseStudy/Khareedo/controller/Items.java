package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.service.ItemsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class Items {
    private ItemsSer itemsSer;

    @Autowired
    public Items(ItemsSer itemsSer) {
        this.itemsSer = itemsSer;
    }


//    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @PostMapping(path = "/add")
    public String addNewItem(@RequestBody Items items) {
        itemsSer.addItem(items);
        return "Service hit!!";
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public Items getItemById(@RequestParam("id") Long id) {
        return itemsSer.getById(id);
    }

    @GetMapping(path = "/get-items/{category}", produces = "application/json")
    @ResponseBody
    public List<Items> getItemsByCategoryAndPrice(@PathVariable(value = "category") String category,
                                                  @RequestParam(value = "lower", required = false) Double lower,
                                                  @RequestParam(value = "higher", required = false) Double higher) {
        if (lower == null && higher == null) {
            return itemsSer.getByCategory(category);
        }
        return itemsSer.getByCategoryAndPrice(category, lower, higher);
    }

    @GetMapping(path = "/get-items/", produces = "application/json")
    @ResponseBody
    public Items getItemsByPrice(@RequestParam(value = "id") Long id) {
        return itemsSer.getById(id);
    }
}
