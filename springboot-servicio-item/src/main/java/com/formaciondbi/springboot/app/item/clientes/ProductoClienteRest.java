package com.formaciondbi.springboot.app.item.clientes;

import com.formaciondbi.springboot.app.item.models.entity.Producto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {

    @GetMapping
    public List<Producto>findAll();

    @GetMapping("/{prodId}")
    public Producto findById(@PathVariable Long prodId);
}
