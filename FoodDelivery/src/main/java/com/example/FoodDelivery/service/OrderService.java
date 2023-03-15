package com.example.FoodDelivery.service;

import com.example.FoodDelivery.model.Order;
import com.example.FoodDelivery.repository.OrderRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    public int saveorder(Order order) {
        Order order1=orderRepository.save(order);
        return order1.getOrderId();
    }

    public JSONArray getuserById(String userId) {
        JSONArray jsonArray=new JSONArray();
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
