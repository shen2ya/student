package com.student.controller;


import com.student.entity.User;
import com.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public Map<String,Object> add(@RequestBody User user) {
        return userService.register(user);
    }

    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping("/update")
    public Map<String,Object> update(@RequestBody User user) {
        return userService.update(user);
    }

    @RequestMapping("/getList")
    public Map<String,Object> getList(@RequestBody User user) {
        return userService.getList(user);
    }

    @RequestMapping("/getUserBuild")
    public Map<String,Object> getUserBuild(@RequestBody User user) {
        return userService.getUserBuild(user);
    }

    @RequestMapping("/getUserClass")
    public Map<String,Object> getUserClass(@RequestBody User user) {
        return userService.getUserClass(user);
    }

    @RequestMapping("/updateUserBuild")
    public Map<String,Object> updateUserBuild(@RequestBody User user) {
        return userService.updateUserBuild(user);
    }

    @RequestMapping("/updateUserClass")
    public Map<String,Object> updateUserClass(@RequestBody User user) {
        return userService.updateUserClass(user);
    }
}
