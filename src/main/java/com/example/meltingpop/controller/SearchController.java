package com.example.meltingpop.controller;

import com.example.meltingpop.dto.AdminBoardDto;
import com.example.meltingpop.dto.BoardDto;
import com.example.meltingpop.entity.Board;
import com.example.meltingpop.service.AdminBoardService;
import com.example.meltingpop.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    BoardService boardService;
    @Autowired
    AdminBoardService adminBoardService;

    @GetMapping("/search")
    public String search(@RequestParam("search_title") String search_title, Board board, Model model){
        //java 리스트 null 체크는 ==null 이 아닌 .isEmpty() 로 체크하자
        if (adminBoardService.getPost(search_title).isEmpty()){
            System.out.println("검색 값이 없습니다.");
            model.addAttribute("search_title",search_title);
            return "static/pages/search-result-none";

        }

        else {
            List<AdminBoardDto> adminBoardDtoList = adminBoardService.getPost(search_title);
            model.addAttribute("song_list",adminBoardDtoList);

            return "static/pages/search-result";
        }
    }
}
