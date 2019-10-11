package com.caseStudy.Khareedo.controller;

import com.caseStudy.Khareedo.Exception.ResourceNotFoundException;
import com.caseStudy.Khareedo.model.items;
import com.caseStudy.Khareedo.repo.Itemrepositry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ItemController {

    @Autowired
 Itemrepositry itemrepositry;

    @GetMapping("/get_details")
    public List<items> getAllNotes() {
        return itemrepositry.findAll();
    }

    @PostMapping("/add_products")
    public items createNote(@Valid @RequestBody items item) {
        return itemrepositry.save(item);
    }

    @GetMapping("/productdetails/{id}")
    public items getNoteById(@PathVariable(value = "id") Long noteId) {
        return itemrepositry.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @GetMapping("/productname/{name}")
    public List<items> getbyname(@PathVariable(value = "name") String name){
        return  itemrepositry.findAllByName(name);
    }
    @GetMapping("/productcategory/{category}")
    public List<items> getbycatogory(@PathVariable(value = "category") String category){
        return  itemrepositry.findAllByCategory(category);
    }
    @PutMapping("/updateitem/{id}")
    public items updateNote(@PathVariable(value = "id") Long noteId,
                            @Valid @RequestBody items noteDetails) {

        items note = itemrepositry.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setCategory(noteDetails.getCategory());
        note.setSubcategory(noteDetails.getSubcategory());
        note.setActive(noteDetails.getActive());
        note.setPrice(noteDetails.getPrice());
        note.setDetails(noteDetails.getDetails());
        note.setImage(noteDetails.getImage());
        note.setName(noteDetails.getName());
        note.setDetails(noteDetails.getDetails());

        items updatedNote = itemrepositry.save(note);
        return updatedNote;
    }

}
