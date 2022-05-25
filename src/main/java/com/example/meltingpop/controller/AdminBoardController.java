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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class  AdminBoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private AdminBoardService adminBoardService;

    public AdminBoardController(){}

    public AdminBoardController(BoardService boardService){
        this.boardService = boardService;
    }

    public AdminBoardController(AdminBoardService adminBoardService){
        this.adminBoardService = adminBoardService;
    }

    @GetMapping("/admin-list")
    public String adminList(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("admin_list",boardDtoList);

        return "static/pages/admin-index";
    }

    @GetMapping("/admin-post")
    public String adminSeePost(@RequestParam("song_title")String song_title, @RequestParam("writer")String writer, Model model) {

        // Board 데이터에서 가져온 데이터를 view에 보여주는 역할
        List<BoardDto> boardDtoList = boardService.getPostFromWriter(song_title,writer);
        BoardDto boardDto = boardDtoList.get(0);
        System.out.println("admin-post 접근 성공");
        model.addAttribute("board_title",boardDto.getBoardTitle());
        model.addAttribute("board_singer",boardDto.getSinger());
        model.addAttribute("board_engLyric",boardDto.getEnglishLyric());
        model.addAttribute("board_korLyric",boardDto.getKoreanLyric());
        model.addAttribute("board_createdDate",boardDto.getBoardCreatedDate());
        model.addAttribute("board_writer",boardDto.getBoardWriter());

        System.out.println(boardDto.getBoardTitle());
        System.out.println(boardDto.getBoardWriter());
        System.out.println(boardDto.getSinger());
        return "static/pages/admin-request";
    }

    @PostMapping("/admin-post")
    public String adminPostToIndex(@RequestParam("writer") String writer, @RequestParam("song_title") String edit_english_title, @RequestParam("board_korLyric")String changedKorLyric){
        // Board 데이터에서 가져온 데이터를 AdminBoard column에 연결시켜서 저장하기
        System.out.println(writer + " " + edit_english_title);
        List<BoardDto> boardDtoList = boardService.getPostFromWriter(edit_english_title,writer);
        BoardDto boardDto = boardDtoList.get(0);

        if(adminBoardService.getPost(edit_english_title).isEmpty()){

            adminBoardService.boardToAdminSave(boardDto);
            boardService.deleteBoard(boardDto);
        }
        else{
            adminBoardService.updatePost(changedKorLyric, writer, edit_english_title);
            boardService.deleteBoard(boardDto);
            System.out.println("업데이트 성공");
        }

        return "redirect://localhost:8081/admin-list";
    }

}
