package com.pragya.oimspro.user.resolver;


import com.pragya.oimspro.user.entity.User;
import com.pragya.oimspro.user.repository.UserRepository;
import com.pragya.oimspro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserResolver {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("/get") //test handle
    public String  get() {
        return "ankit";
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
