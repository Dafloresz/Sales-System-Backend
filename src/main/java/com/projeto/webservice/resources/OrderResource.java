package com.projeto.webservice.resources;

import com.projeto.webservice.entities.Order;
import com.projeto.webservice.entities.User;
import com.projeto.webservice.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping (value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orderList = service.findAll();
        return ResponseEntity.ok().body(orderList);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order orderById = service.findByiId(id);
        return ResponseEntity.ok().body(orderById);
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order order){
        order = service.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
