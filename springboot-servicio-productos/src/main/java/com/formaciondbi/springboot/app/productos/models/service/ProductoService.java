package com.formaciondbi.springboot.app.productos.models.service;

import com.formaciondbi.springboot.app.productos.models.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> findAll();
    public Producto findById(Long prodId);

}
