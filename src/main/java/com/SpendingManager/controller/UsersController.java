package com.SpendingManager.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class UsersController {

    public List<Map<String, String>> users = new ArrayList<Map<String, String>>();
    long usercount = 0;

    @GetMapping("/users")
    public List<Map<String,String>> getAllUsers(){
        return users;
    }

    @GetMapping("/user/{id}")
    public Map<String, String> getUser(@PathVariable String id){
        return users.stream().filter(user -> user.get("id").equals(id)).findFirst().orElseThrow();
    }

    @PostMapping("/user")
    public Map<String, String> addUser(@RequestBody Map<String, String> user){
        user.put("id", String.valueOf(usercount++));
        users.add(user);
        return user;
    }
    @PutMapping("/user/{id}")
    public Map<String, String> changeUser(@PathVariable String id, @RequestBody Map<String, String> user){
        Map<String,String> userFromBd = users.get(Integer.parseInt(id));
        userFromBd.putAll(user);
        userFromBd.put("id", id);
        return userFromBd;

    }
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable String id){
        Map<String, String> user = users.get(Integer.parseInt(id));
        users.remove(user);
    }

}
