package com.example.meltingpop.repository;

import com.example.meltingpop.entity.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
/*
    @Test
    public void getBoard(){
        Board board = Board.builder()
                .writer("joonghyun")
                .singer("joonghyun")
                .songtitle("hello")
                .english_lyric("hello")
                .korean_lyric("안녕")
                .createdDate(LocalDateTime.now())
                .build();
        boardRepository.save(board);

        List<Board> boardList = boardRepository.findAll();

        System.out.println(boardList.get(0).getSongtitle());

    }
    */

    @Test
    public void deleteAllBoard(){
        boardRepository.deleteAll();
    }
}

