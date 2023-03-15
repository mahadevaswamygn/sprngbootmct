package com.example.FoodDelivery.repository;

import com.example.FoodDelivery.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Integer> {
}
