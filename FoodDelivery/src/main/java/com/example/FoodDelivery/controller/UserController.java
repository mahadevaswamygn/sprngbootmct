package com.example.FoodDelivery.controller;

import com.example.FoodDelivery.model.User;
import com.example.FoodDelivery.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @Autowired
    UserService userService;



    @PostMapping(value = "/addUser")
    public ResponseEntity<String> adduser(@RequestBody String userData)
    {
        User user = setUserData(userData);

       int id= userService.saveUser(user);
        return new ResponseEntity<String>("user saved with id"+id,HttpStatus.CREATED);
    }

    private User setUserData(String userData) {

        JSONObject jobj = new JSONObject(userData);
        User user= new User();
        user.setUserName(jobj.getString("userName"));
        user.setAdminSate(jobj.getInt("adminState"));
        user.setEmail(jobj.getString("email"));
        user.setPhoneNumber(jobj.getString("phoneNumber"));
        return user;

    }



    public ResponseEntity<String> getUserById(@RequestParam String userId)
    {
        JSONArray jary=userService.getByIdthree(userId);
        return new ResponseEntity<String >(jary.toString(), HttpStatus.OK);
    }



}
