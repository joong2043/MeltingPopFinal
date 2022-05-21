package com.example.meltingpop.controller;

import com.example.meltingpop.dto.UserDto;
import com.example.meltingpop.entity.User;
import com.example.meltingpop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {
    private UserService userService;

    public JoinController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value="/join")
    public String join(){
        return "static/pages/join";
    }

    @PostMapping("/join")
    public String join(UserDto userDto){
        userService.join(userDto);

        return "static/pages/login";
    }
}