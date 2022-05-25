package com.example.meltingpop.controller;

import com.example.meltingpop.dto.AdminBoardDto;
import com.example.meltingpop.dto.BoardDto;
import com.example.meltingpop.entity.Song_Info;
import com.example.meltingpop.entity.User;
import com.example.meltingpop.service.AdminBoardService;
import com.example.meltingpop.service.BoardService;
import com.example.meltingpop.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.meltingpop.session.SessionConst.LOGIN_USER;


@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private SongService songService;
    @Autowired
    private AdminBoardService adminBoardService;

    @GetMapping("/list")
    public String list(Model model){
        //postList 파람을 받아서 데이터베이스에 있는 내용
        List<BoardDto> boardDtoList = boardService.getBoardList();
        for(BoardDto boardDto1:boardDtoList){
            System.out.println(boardDto1);
        }
        return "static/index";
    }

    @GetMapping("/post-lyric")
    public String noneToPost(@RequestParam("search_title")String search_title, Model model){

        Song_Info song_info = songService.getSongData(search_title);

        model.addAttribute("song_info_title",song_info.getSong());
        model.addAttribute("song_info_singer",song_info.getSinger());
        model.addAttribute("song_info_engLyric",song_info.getLyric());
        model.addAttribute("song_info_korLyric",song_info.getKoreanLyric());



        System.out.println("noneToPost :" + search_title);

        return "static/pages/post-lyrics";
    }


    @PostMapping("/post-lyric")
    public String postToAdmin(@RequestParam("edit-english-title") String edit_english_title, @RequestParam("edit-english-lyrics") String edit_english_lyrics, @RequestParam("edit-korean-lyrics")String edit_korean_lyrics, HttpServletRequest request,Model model){
        HttpSession session = request.getSession(false);
        //User nowUSer = (User) session.getAttribute(LOGIN_USER);
        Song_Info song_info = songService.getSongData(edit_english_title);


        BoardDto boardDto = BoardDto.builder()
                .boardTitle(edit_english_title)
                .boardWriter(((User) session.getAttribute(LOGIN_USER)).getUserId())
                .singer(song_info.getSinger())
                .englishLyric(edit_english_lyrics)
                .koreanLyric(edit_korean_lyrics)
                .boardCreatedDate(LocalDateTime.now())
                .build();
        boardService.savePost(boardDto);


        model.addAttribute("userName",((User) session.getAttribute(LOGIN_USER)).getUserId());

        return "static/pages/main";
    }

    @GetMapping("/view-lyric")
    public String viewLyric(@RequestParam("song_title")String song_title, @RequestParam("singer")String singer, Model model){
        List<AdminBoardDto> adminBoardDtoList = adminBoardService.getPost(song_title);
        AdminBoardDto adminBoardDto =adminBoardDtoList.get(0);

        model.addAttribute("song_title",adminBoardDto.getBoardTitle());
        model.addAttribute("singer",adminBoardDto.getSinger());
        model.addAttribute("english_lyric",adminBoardDto.getEnglishLyric());
        model.addAttribute("korean_lyric",adminBoardDto.getKoreanLyric());
        model.addAttribute("writer",adminBoardDto.getBoardWriter());
        model.addAttribute("created_date",adminBoardDto.getBoardCreatedDate());

        return "static/pages/view-lyrics";
    }


    @GetMapping("/edit-lyric")
    public String editLyric(@RequestParam("song_title")String song_title,Model model){
        List<AdminBoardDto> adminBoardDtoList = adminBoardService.getPost(song_title);
        AdminBoardDto adminBoardDto =adminBoardDtoList.get(0);

        model.addAttribute("song_title",adminBoardDto.getBoardTitle());
        model.addAttribute("singer",adminBoardDto.getSinger());
        model.addAttribute("english_lyric",adminBoardDto.getEnglishLyric());
        model.addAttribute("korean_lyric",adminBoardDto.getKoreanLyric());
        model.addAttribute("writer",adminBoardDto.getBoardWriter());
        model.addAttribute("created_date",adminBoardDto.getBoardCreatedDate());

        return "static/pages/edit-lyrics";
    }

    @PostMapping("/edit-lyric")
    public String edittedLyricToAdmin(@RequestParam("edit-english-title")String song_title, @RequestParam("edit-korean-lyrics")String edit_korean_lyrics, @RequestParam("edit-english-lyrics")String edit_english_lyrics, HttpServletRequest request){
        HttpSession session = request.getSession(false);
//        System.out.println(((User) session.getAttribute(LOGIN_USER)).getUserId());

        List<AdminBoardDto> adminBoardDtoList = adminBoardService.getPost(song_title);
        AdminBoardDto adminBoardDto =adminBoardDtoList.get(0);

        BoardDto boardDto = BoardDto.builder()
                .boardWriter(((User) session.getAttribute(LOGIN_USER)).getUserId())
                .boardTitle(song_title)
                .boardCreatedDate(LocalDateTime.now())
                .singer(adminBoardDto.getSinger())
                .englishLyric(edit_english_lyrics)
                .koreanLyric(edit_korean_lyrics)
                .build();

        boardService.savePost(boardDto);

        return "redirect://localhost:8081";
    }


/*
    @PostMapping("/post")
    public String write(@RequestBody BoardDto boardDto,Model model){

        boardService.savePost(boardDto);
        return "static/index";
    }


    @GetMapping("/post/{song_title}")
    public String detail(@PathVariable("song_title") String song_title){
        List<BoardDto> boardDtoList = boardService.getPost(song_title);
        System.out.println("게시판 "+song_title+" 읽기 성공");

        return "static/index";
    }

    @GetMapping("/post/edit/{song_title}")
    public String edit(@PathVariable("song_title") String song_title){
        List<BoardDto> boardDtoList = boardService.getPost(song_title);
        //boardService.savePost(boardDtoList);
        System.out.println("게시판(수정) "+song_title+" 읽기 성공");
        return "static/index";
    }

    @PutMapping("/post/edit/{boardNum}")
    public String update(@Param("changedContent") String changedContent, @PathVariable("song_title") String song_title){
        boardService.updatePost(changedContent, song_title);
        System.out.println("게시판 수정 성공");
        return "redirect:/";
    }

    @DeleteMapping("/post/{boardNum}")
    public String delete(@PathVariable("boardNum") Long boardNum){
        boardService.deletePost(boardNum);
        System.out.println("게시판 "+boardNum+"번 삭제 성공");
        return "redirect:/";
    }

 */
}
