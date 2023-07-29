package com.vinicius.course.services;

import com.vinicius.course.entities.Order;
import com.vinicius.course.repositories.OrderRepository;
import com.vinicius.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> obj =  orderRepository.findById(id);
        return obj.get();
    }

    public Order insert(Order obj){
        if(obj.getPayment() != null){
            obj.getPayment().setOrder(obj);
        }
        return orderRepository.save(obj);
    }

    public void delete(Long id){
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public Order update(Long id, Order obj){
        try {
            Order entity = orderRepository.getReferenceById(id);
            entity.setOrderStatus(obj.getOrderStatus());
            return orderRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
