package com.example.FoodDelivery.controller;

import com.example.FoodDelivery.model.Food;
import com.example.FoodDelivery.model.User;
import com.example.FoodDelivery.repository.UserREpository;
import com.example.FoodDelivery.service.FoodService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FoodController {

    @Autowired
    FoodService foodService;

    @Autowired
    UserREpository userREpository;

    @PostMapping(value = "/addFood")
    public ResponseEntity<String> addfood(@RequestBody String fooddata)
    {
        Food food=setFooddata(fooddata);
        if(food.getAdminstate() != 2)
        {
            return new ResponseEntity<String >("user is not a Admin", HttpStatus.BAD_REQUEST);
        }
        int fid=foodService.savefood(food);
        return new ResponseEntity<String>("Food saved with id "+fid,HttpStatus.CREATED);
    }

    private Food setFooddata( String fooddata) {
        JSONObject jobj = new JSONObject(fooddata);
        Food food=new Food();
        food.setFoodName(jobj.getString("foodName"));
        food.setAdminstate(jobj.getInt("adminState"));
        return  food;
    }
    @PutMapping("/update/{userId}/{foodid}")
    public ResponseEntity<String> updatefood(@PathVariable String userId,@PathVariable String foodid,@RequestBody String updatedata)
    {
        Food food=setFooddata(updatedata);

        User user=null;
        if(userREpository.findById(Integer.valueOf(userId)).isPresent())
        {
            user=userREpository.findById(Integer.valueOf(userId)).get();
        }
        if(user.getAdminSate() != 2)
        {
            return new ResponseEntity<String>("not a admin",HttpStatus.BAD_REQUEST);
        }

        foodService.updatedeatails(userId,foodid,food);
        return new ResponseEntity<String>("food updated",HttpStatus.CREATED);



    }
    @DeleteMapping(value = "/deletefood/userId/{userId}")
    public ResponseEntity<String> deletfood(@PathVariable String userId,@RequestParam String foodId)
    {
        User user= userREpository.findById(Integer.valueOf(userId)).get();
        if(user.getAdminSate() != 2)
        {
            return new ResponseEntity<String>("user is not a Admin",HttpStatus.BAD_REQUEST);
        }
       foodService.deletefood(foodId);
        return new ResponseEntity<String>("deleted",HttpStatus.OK);

    }


    public ResponseEntity<String> getAllfoods(@RequestParam String userId)
    {
        User user=userREpository.findById(Integer.valueOf(userId)).get();
        if(user.getAdminSate() != 2)
        {
            return new ResponseEntity<>("this user is nit a Admin",HttpStatus.BAD_REQUEST);
        }
        JSONArray jsonarray=foodService.getAll();
        return new ResponseEntity<String>(jsonarray.toString(),HttpStatus.OK);
    }


}
