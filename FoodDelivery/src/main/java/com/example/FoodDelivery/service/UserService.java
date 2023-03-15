package com.example.FoodDelivery.service;

import com.example.FoodDelivery.model.Order;
import com.example.FoodDelivery.model.User;
import com.example.FoodDelivery.repository.OrderRepository;
import com.example.FoodDelivery.repository.UserREpository;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserREpository userREpository;

    @Autowired
    OrderRepository orderRepository;

    public int saveUser(User user) {
        User user1=userREpository.save(user);
        return user1.getUserId();


    }

    public JSONArray getByIdthree(String userId) {
        JSONArray jarray = new JSONArray();
        List<Order> orderList = orderRepository.findAll();
        for (Order order :orderList) {
            if(order.getUser().getUserId() == Integer.valueOf(userId) ){
                JSONObject jsonObject=new JSONObject(order);
                jarray.put(jsonObject);
            }

        }
        return jarray;
    }
}
