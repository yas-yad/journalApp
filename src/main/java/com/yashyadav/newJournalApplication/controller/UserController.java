package com.yashyadav.newJournalApplication.controller;

import com.yashyadav.newJournalApplication.entity.User;
import com.yashyadav.newJournalApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired private UserService userService;



//
//    @GetMapping("/")
//    public List<User> getAllUser(){
//        return userService.getAll();
//    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok("you are logged in as : "+authentication.getName());
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User old=userService.findByUserName(userName);
        old.setUserName(user.getUserName());
        old.setPassword(user.getPassword());
        userService.saveNewUser(old);
        return ResponseEntity.badRequest().body("no data found");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(authentication.getName());
        return ResponseEntity.ok().body("delete successfully");
    }
}
