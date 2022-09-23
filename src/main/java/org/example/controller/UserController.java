package org.example.controller;

import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;


    @PutMapping(path = "/user/signup/{username}/{password}")
    public String signup(@PathVariable("username") String username, @PathVariable("password") String password) {
        String result = null;
        int signupCode = userService.signup(username, password);
        switch (signupCode) {
            case 0:
                result = "succ";
                break;
            case -1:
                result = "param error";
                break;
            case -2:
                result = "user exists";
                break;
            case -3:
                result = "password not  valid;";
                break;
        }
        return result;
    }

    @PostMapping(path = "/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String result = null;

        return result;
    }


}
