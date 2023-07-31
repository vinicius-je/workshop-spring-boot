package com.vinicius.course.repositories;

import com.vinicius.course.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM TB_ORDER WHERE client_id = :userId", nativeQuery = true)
    List<Order> findOrdersByUser(@Param("userId") Long userId);
}
