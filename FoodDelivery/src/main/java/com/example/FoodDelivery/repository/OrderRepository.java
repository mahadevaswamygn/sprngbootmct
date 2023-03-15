package com.example.FoodDelivery.repository;

import com.example.FoodDelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
