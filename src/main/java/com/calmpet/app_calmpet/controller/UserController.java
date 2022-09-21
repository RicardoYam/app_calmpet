package com.calmpet.app_calmpet.controller;

import com.calmpet.app_calmpet.entity.UserEntity;
import com.calmpet.app_calmpet.model.User;
import com.calmpet.app_calmpet.service.UserService;
import com.calmpet.app_calmpet.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calmpet")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findUser")
    public Result getAllUsers(@RequestBody UserEntity userEntity) {
        return userService.getAllUsers(userEntity);
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody UserEntity userEntity) {
        return userService.addUser(userEntity);
    }

    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody UserEntity userEntity) {
        return userService.updateUser(userEntity);
    }

    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestBody UserEntity userEntity) {
        return userService.removeUser(userEntity);
    }
}
