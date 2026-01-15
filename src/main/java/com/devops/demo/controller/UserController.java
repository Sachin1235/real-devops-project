package com.devops.demo.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.devops.demo.model.*;
import com.devops.demo.repository.*;
@RestController
@RequestMapping("/users")
public class UserController {
 private final UserRepository userRepo;
 public UserController(UserRepository u){this.userRepo=u;}
 @PostMapping
 public User createUser(@RequestBody User user){
   return userRepo.save(user);
 }
 @GetMapping
 public List<User> allUsers(){
   return userRepo.findAll();
 }
}
