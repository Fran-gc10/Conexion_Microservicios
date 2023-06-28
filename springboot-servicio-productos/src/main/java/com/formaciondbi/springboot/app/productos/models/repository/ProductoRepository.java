package com.formaciondbi.springboot.app.productos.models.repository;

import com.formaciondbi.springboot.app.productos.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto,Long> {
}
