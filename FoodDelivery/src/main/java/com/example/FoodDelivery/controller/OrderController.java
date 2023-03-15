package com.example.FoodDelivery.controller;

import com.example.FoodDelivery.model.Food;
import com.example.FoodDelivery.model.Order;
import com.example.FoodDelivery.model.User;
import com.example.FoodDelivery.repository.FoodRepository;
import com.example.FoodDelivery.repository.OrderRepository;
import com.example.FoodDelivery.repository.UserREpository;
import com.example.FoodDelivery.service.FoodService;
import com.example.FoodDelivery.service.OrderService;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    UserREpository userREpository;

    @PostMapping(value = "/orderFood")
    public ResponseEntity<String>  orderfood (@RequestBody String orderdata)
    {
        Order order=setOrder(orderdata);
        if(order == null)
        {
            return new ResponseEntity<String>("foodId or UserId is not valid",HttpStatus.BAD_REQUEST);
        }
        int oid=orderService.saveorder(order);
        return new ResponseEntity<String>("oder saved/booked with id "+oid, HttpStatus.CREATED);
    }

    private Order setOrder(String orderdata) {
        JSONObject jsonObject=new JSONObject(orderdata);
        Order order=new Order();
        Food food=null;
        int fid= jsonObject.getInt("foodid");
        if(foodRepository.findById(fid).isPresent())
        {
            food=foodRepository.findById(fid).get();
        }
        else {
            return null;
        }
        int uid=jsonObject.getInt("userId");
        User user=null;
        if(userREpository.findById(uid).isPresent())
        {
            user=userREpository.findById(uid).get();
        }
        else
        {
            return null;
        }
        order.setFood(food);
        order.setUser(user);

        return order;


    }

    @GetMapping(value = "/getOrdersByUserId")
    public ResponseEntity<String> getOrdersbyId(@RequestParam String userId)
    {
        JSONArray jsonArray=orderService.getuserById(userId);
        return new ResponseEntity<String>(jsonArray.toString(),HttpStatus.OK);
    }



}
