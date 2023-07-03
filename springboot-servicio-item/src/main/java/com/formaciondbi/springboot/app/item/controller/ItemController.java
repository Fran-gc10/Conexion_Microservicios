package com.formaciondbi.springboot.app.item.controller;

import com.formaciondbi.springboot.app.item.models.entity.Item;
import com.formaciondbi.springboot.app.item.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @GetMapping
    public List<Item> findAll(){
        return itemService.findAll();
    }

    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item findById(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.finById(id, cantidad);
    }
}
