package com.formaciondbi.springboot.app.item.controller;

import com.formaciondbi.springboot.app.item.models.entity.Item;
import com.formaciondbi.springboot.app.item.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;


    @GetMapping                 //INYECTAMOS LOS PARAMETROS DEFINIDOS ENB EL ARCHIVO CONFIGURACION DEL FILTRO
    public List<Item> findAll(@RequestParam(name = "nombre", required = false) String nombre, @RequestHeader (name = "token-request", required = false) String token){
        System.out.println(nombre);
        System.out.println(token);
        return itemService.findAll();
    }

    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item findById(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.finById(id, cantidad);
    }
}
