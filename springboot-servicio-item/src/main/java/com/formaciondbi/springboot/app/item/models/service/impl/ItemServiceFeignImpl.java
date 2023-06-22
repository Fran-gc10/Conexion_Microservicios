package com.formaciondbi.springboot.app.item.models.service.impl;

import com.formaciondbi.springboot.app.item.clientes.ProductoClienteRest;
import com.formaciondbi.springboot.app.item.models.entity.Item;
import com.formaciondbi.springboot.app.item.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")    //Con esto y la anoracio "Qualify" en el controller nos aseguramos de que se cumpla la prioridad de inyeccion
@Primary    //Con esta anotacion se define que servicio se consume cuando se llama a la interfaz
public class ItemServiceFeignImpl implements ItemService {

    @Autowired
    ProductoClienteRest productoClienteRest;


    @Override
    public List<Item> findAll() {
        return productoClienteRest.findAll().stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item finById(Long itemId, Integer cantidad) {
        return new Item(productoClienteRest.findById(itemId), cantidad);
    }
}
