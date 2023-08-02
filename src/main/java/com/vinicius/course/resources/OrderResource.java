package com.vinicius.course.resources;

import com.vinicius.course.entities.Order;
import com.vinicius.course.services.OrderItemService;
import com.vinicius.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/{id}")
    public Order findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<Order>> findOrdersById(@PathVariable Long id){
        List<Order> list = orderService.findOrdersById(id);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order obj){
        Order order = orderService.insert(obj);

        orderItemService.insert(obj, obj.getItems());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj){
        Order order = orderService.update(id, obj);
        return ResponseEntity.ok().body(order);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
