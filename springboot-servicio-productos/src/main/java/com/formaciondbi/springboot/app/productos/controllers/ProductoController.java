package com.formaciondbi.springboot.app.productos.controllers;

import com.formaciondbi.springboot.app.productos.models.entity.Producto;
import com.formaciondbi.springboot.app.productos.models.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    @Autowired
    private Environment env;
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto>findAll(){
        return productoService.findAll().stream().map(producto -> {
            producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            return producto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{prodId}")
    public Producto findById(@PathVariable Long prodId) throws InterruptedException {

        if (prodId.equals(10L)) {
            throw new IllegalStateException("Producto no encontrado");
        }
        if (prodId.equals(7L)) {
            TimeUnit.SECONDS.sleep(5L);
            throw new IllegalStateException("Producto no encontrado");
        }

        Producto producto=productoService.findById(prodId);
        producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        return producto;
    }

    //TODO Comprobar que genere bien los puertos --> TODO PERFE
}
