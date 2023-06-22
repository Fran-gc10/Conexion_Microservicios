package com.formaciondbi.springboot.app.item.models.service;

import com.formaciondbi.springboot.app.item.models.Item;
import com.formaciondbi.springboot.app.item.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/productos", Producto[].class));
        return productos.stream().map(p->new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item finById(Long itemId, Integer cantidad) {
        //Realizamos un mapeo d ela variable para poder introducirla en la url como parametro
        Map<String,String> pathVariables=new HashMap<String,String>();
        pathVariables.put("prodId", itemId.toString());
        Producto producto=clienteRest.getForObject("http://localhost:8001/productos/{prodId}",Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }
}
