package com.SpendingManager.controller;

import com.SpendingManager.model.User;
import com.SpendingManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
public class UsersController {

    @Autowired
    private UserRepository userRepo;


    @GetMapping("/user/{id}")
    public Map<String, String> getUser(@PathVariable Integer id){
        User u = userRepo.findById(id).get();
        Map<String, String> res = new HashMap<>();
        res.put("id", String.valueOf(u.getId()));
        res.put("login", u.getLogin());
        return res;
    }
    @GetMapping("/user")
    public List<Map<String, String>> getUserId(@RequestParam String login){
        List<User> users = userRepo.findByLogin(login);
        List<Map<String, String>> res = new ArrayList<>();

        for (User u:users){
            Map<String, String> user = new HashMap<String, String>();
            user.put("id", String.valueOf(u.getId()));
            user.put("login", u.getLogin());
            res.add(user);
        }
        return res;
    }

    @PostMapping("/register")
    public Map<String, String> addUser(@RequestBody Map<String, String> body){
        String login = body.get("login");
        User u = new User(login);
        userRepo.save(u);
        Map<String, String> res = new HashMap<>();
        res.put("id", String.valueOf(u.getId()));
        res.put("login", u.getLogin());
        return res;
    }
    @PutMapping("/user/{id}")
    public Map<String, String> changeUser(@PathVariable Integer id, @RequestParam String login){
        User userFromBd = userRepo.findById(id).get();
        userFromBd.setLogin(login);
        userRepo.save(userFromBd);
        Map<String, String> res = new HashMap<>();
        res.put("id", String.valueOf(userFromBd.getId()));
        res.put("login", userFromBd.getLogin());
        return res;

    }
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Integer id){
        User u = userRepo.findById(id).get();
        userRepo.delete(u);
    }

}
