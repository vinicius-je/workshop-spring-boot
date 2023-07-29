package com.vinicius.course.services;

import com.vinicius.course.entities.Order;
import com.vinicius.course.entities.OrderItem;
import com.vinicius.course.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> insert(Order order, Set<OrderItem> itens){
        for(OrderItem oi : itens){
            oi.setOrder(order);
        }
        return orderItemRepository.saveAll(itens);
    }
}
