package com.example.meltingpop.controller;

import com.example.meltingpop.entity.User;
import com.example.meltingpop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.meltingpop.session.SessionConst.LOGIN_USER;

@Controller
public class HomeController {
    @RequestMapping("/forgot")
    public String home1(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        System.out.println("세션 확인: "+session.getAttribute(LOGIN_USER));
        return "/static/pages/forgot-pw";
    }
    @GetMapping("/")
    public String home2(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session==null){
            return "/static/index";
        }
        else{
            return "/templates/main";
        }
    }
    @GetMapping("/admin-index")
    public String home3(){
        return "static/pages/admin-index";
    }
}

