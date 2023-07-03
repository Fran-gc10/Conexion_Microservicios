package com.formaciondbi.springboot.app.productos.controllers;

import com.formaciondbi.springboot.app.productos.models.entity.Producto;
import com.formaciondbi.springboot.app.productos.models.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto>findAll(){
        return productoService.findAll();
    }

    @GetMapping("/{prodId}")
    public Producto findById(@PathVariable Long prodId){
        return productoService.findById(prodId);
    }
}
