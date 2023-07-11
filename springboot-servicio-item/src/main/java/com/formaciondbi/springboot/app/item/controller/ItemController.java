package com.formaciondbi.springboot.app.item.controller;

import com.formaciondbi.springboot.app.item.models.entity.Item;
import com.formaciondbi.springboot.app.item.models.entity.Producto;
import com.formaciondbi.springboot.app.item.models.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private CircuitBreakerFactory cbFactory;

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
        return cbFactory.create("items")
                .run(() -> itemService.finById(id, cantidad), e -> metodoAlternativo(id, cantidad, e));
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable e){
        logger.info(e.getMessage());
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Alternativo");
        producto.setPrecio(100.00);
        item.setProducto(producto);

        return item;
    }
}
