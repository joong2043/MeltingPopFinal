package com.example.meltingpop.controller;

import com.example.meltingpop.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminBoardController {
    private BoardService boardService;

    public AdminBoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/admin-list")
    public String adminList(Model model){

        return "static/pages/admin-index";
    }

}
