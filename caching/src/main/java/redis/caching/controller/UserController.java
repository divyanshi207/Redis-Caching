package redis.caching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.caching.entity.User;
import redis.caching.repository.UserRepository;
import redis.caching.service.UserService;


@RestController
@RequestMapping("/cache")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-user/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @DeleteMapping("/remove-user/{id}")
    public String removeUser(@PathVariable int id){return userService.removeUser(id);}
    @PostMapping("/add-user")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
