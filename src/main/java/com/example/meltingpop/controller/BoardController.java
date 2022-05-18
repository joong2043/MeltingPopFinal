package com.example.meltingpop.controller;

import com.example.meltingpop.dto.BoardDto;
import com.example.meltingpop.repository.BoardRepository;
import com.example.meltingpop.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    private BoardService boardService;


    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String list(Model model){
        //postList 파람을 받아서 데이터베이스에 있는 내용
        List<BoardDto> boardDtoList = boardService.getBoardList();
        for(BoardDto boardDto1:boardDtoList){
            System.out.println(boardDto1);
        }

        return "static/index";
    }

    @GetMapping("/post")
    public String post(){
        return "static/index";
    }

    @PostMapping("/post")
    public String write(@RequestBody BoardDto boardDto){
        boardService.savePost(boardDto);
        return "static/index";
    }

    @GetMapping("/post/{boardNum}")
    public String detail(@PathVariable("boardNum") Long boardNum){
        BoardDto boardDto = boardService.getPost(boardNum);
        System.out.println("게시판 "+boardNum+"번 읽기 성공");

        return "static/index";
    }

    @GetMapping("/post/edit/{boardNum}")
    public String edit(@PathVariable("boardNum") Long boardNum){
        BoardDto boardDto = boardService.getPost(boardNum);
        boardService.savePost(boardDto);
        System.out.println("게시판(수정) "+boardNum+"번 읽기 성공");
        return "static/index";
    }

    @PutMapping("/post/edit/{boardNum}")
    public String update(@Param("changedContent") String changedContent, @PathVariable("boardNum") Long boardNum){
        boardService.updatePost(changedContent, boardNum);
        System.out.println("게시판 수정 성공");
        return "redirect:/";
    }

    @DeleteMapping("/post/{boardNum}")
    public String delete(@PathVariable("boardNum") Long boardNum){
        boardService.deletePost(boardNum);
        System.out.println("게시판 "+boardNum+"번 삭제 성공");
        return "redirect:/";
    }
}
