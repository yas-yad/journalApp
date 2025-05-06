package com.yashyadav.newJournalApplication.controller;

import com.yashyadav.newJournalApplication.entity.User;
import com.yashyadav.newJournalApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public boolean create(@RequestBody User user){
        return userService.saveNewUser(user);
    }
}
