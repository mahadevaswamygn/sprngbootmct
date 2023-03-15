package com.example.FoodDelivery.service;

import com.example.FoodDelivery.model.Food;
import com.example.FoodDelivery.repository.FoodRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    FoodRepository foodRepository;


    public int savefood(Food food) {
        Food food1=foodRepository.save(food);
        return food1.getFoodId();

    }

    public void updatedeatails(String userId,String foodId, Food food) {
        Food food1=foodRepository.findById(Integer.valueOf(foodId)).get();
        food.setFoodName(food1.getFoodName());
        food.setAdminstate(food1.getAdminstate());
        food.setFoodId(food1.getFoodId());
        foodRepository.save(food);


    }

    public void  deletefood(String foodId) {
        Food food=foodRepository.findById(Integer.valueOf(foodId)).get();
        foodRepository.delete(food);

    }

    public JSONArray getAll() {
        JSONArray jsonArray=new JSONArray();
        List<Food> foodList = foodRepository.findAll();
        for (Food food:foodList) {
            JSONObject jsonObject=new JSONObject(food);
            jsonArray.put(jsonObject);

        }
        return jsonArray;

    }
}
