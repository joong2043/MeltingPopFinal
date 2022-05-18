package com.example.meltingpop.service;

import com.example.meltingpop.dto.BoardDto;
import com.example.meltingpop.entity.Board;
import com.example.meltingpop.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    // BoardRepository 클래스에 있는 객체를 BoardService 클래스에 주입
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    // 게시물 저장하는 메소드
    @Transactional
    public Long savePost(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getBoardNum();
    }

    // Board 데이터에 있는 모든 목록(데이터)를 찾아서 BoardDto에 넣어서 BoardDto에 있는 내용들을 Front 단으로 출력하기 위한 메서드
    @Transactional
    public List<BoardDto> getBoardList(){
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board:boardList){
            BoardDto boardDto = BoardDto.builder()
                    .boardNum(board.getBoardNum())
                    .boardTitle(board.getTitle())
                    .boardWriter(board.getWriter())
                    .boardContent(board.getContent())
                    .boardCreatedDate(board.getCreatedDate())
                    .boardModifiedDate(board.getModifiedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long boardNum){
        Board board = boardRepository.findById(boardNum).get();

        BoardDto boardDto = BoardDto.builder()
                .boardNum(board.getBoardNum())
                .boardWriter(board.getWriter())
                .boardTitle(board.getTitle())
                .boardContent(board.getContent())
                .boardCreatedDate(board.getCreatedDate())
                .boardModifiedDate(board.getModifiedDate())
                .build();

        return boardDto;
    }

    @Transactional
    public void updatePost(String changedContent, Long boardNum){
        boardRepository.updateBoard(changedContent, boardNum);
    }

    @Transactional
    public void deletePost(Long boardNum){
        boardRepository.deleteById(boardNum);
    }

}