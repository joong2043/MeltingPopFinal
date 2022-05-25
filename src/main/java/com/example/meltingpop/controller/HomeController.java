package com.example.meltingpop.controller;

import com.example.meltingpop.dto.AdminBoardDto;
import com.example.meltingpop.entity.User;
import com.example.meltingpop.service.AdminBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.example.meltingpop.session.SessionConst.LOGIN_USER;

@Controller
public class HomeController {
    @Autowired
    AdminBoardService adminBoardService;

    @RequestMapping("/forgot")
    public String home1(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        System.out.println("세션 확인: "+session.getAttribute(LOGIN_USER));
        return "/static/pages/forgot-pw";
    }
    @GetMapping("/")
    public String home2(HttpServletRequest request, Model model){
        List<AdminBoardDto> adminBoardDtoList = adminBoardService.getBoardList();
        model.addAttribute("modified_song_list",adminBoardDtoList);

        HttpSession session = request.getSession(false);

        if (session==null){
            return "/static/index";
        }

        else{
            model.addAttribute("userName",((User) session.getAttribute(LOGIN_USER)).getUserId());
            return "/static/pages/main";
        }

    }
    @GetMapping("/admin-index")
    public String home3(){
        return "static/pages/admin-index";
    }
}

