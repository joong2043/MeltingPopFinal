package com.example.meltingpop.service;

import com.example.meltingpop.dto.AdminBoardDto;
import com.example.meltingpop.dto.BoardDto;
import com.example.meltingpop.entity.AdminBoard;
import com.example.meltingpop.entity.Board;
import com.example.meltingpop.repository.AdminBoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminBoardService {
    private AdminBoardRepository adminBoardRepository;

    public AdminBoardService(AdminBoardRepository adminBoardRepository) {
        this.adminBoardRepository = adminBoardRepository;
    }

    @Transactional
    public Long savePost(AdminBoardDto adminBoardDto) {
        return adminBoardRepository.save(adminBoardDto.toEntity()).getBoardNum();
    }

    // Board 데이터에 있는 모든 목록(데이터)를 찾아서 BoardDto에 넣어서 BoardDto에 있는 내용들을 Front 단으로 출력하기 위한 메서드
    @Transactional
    public List<AdminBoardDto> getBoardList() {
        List<AdminBoard> boardList = adminBoardRepository.findAll();
        List<AdminBoardDto> boardDtoList = new ArrayList<>();

        for (AdminBoard adminBoard : boardList) {
            AdminBoardDto adminBoardDto = AdminBoardDto.builder()
                    .boardNum(adminBoard.getBoardNum())
                    .boardTitle(adminBoard.getSongtitle())
                    .boardWriter(adminBoard.getWriter())
                    .singer(adminBoard.getSinger())
                    .englishLyric(adminBoard.getEnglish_lyric())
                    .koreanLyric(adminBoard.getKorean_lyric())
                    .boardCreatedDate(adminBoard.getCreatedDate())
                    .build();
            boardDtoList.add(adminBoardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public List<AdminBoardDto> getPost(String song_title) {

        List<AdminBoard> boardList = adminBoardRepository.findBySongtitle(song_title);
        List<AdminBoardDto> boardDtoList = new ArrayList<>();

        for (AdminBoard adminBoard : boardList) {
            AdminBoardDto adminBoardDto = AdminBoardDto.builder()
                    .boardNum(adminBoard.getBoardNum())
                    .boardTitle(adminBoard.getSongtitle())
                    .boardWriter(adminBoard.getWriter())
                    .singer(adminBoard.getSinger())
                    .englishLyric(adminBoard.getEnglish_lyric())
                    .koreanLyric(adminBoard.getKorean_lyric())
                    .boardCreatedDate(adminBoard.getCreatedDate())
                    .build();
            boardDtoList.add(adminBoardDto);
        }

        return boardDtoList;
    }

    @Transactional
    public Long boardToAdminSave(BoardDto boardDto){

            AdminBoardDto adminBoardDto = AdminBoardDto.builder()
                    .boardWriter(boardDto.getBoardWriter())
                    .boardTitle(boardDto.getBoardTitle())
                    .singer(boardDto.getSinger())
                    .englishLyric(boardDto.getEnglishLyric())
                    .koreanLyric(boardDto.getKoreanLyric())
                    .boardCreatedDate(LocalDateTime.now())
                    .build();
            return adminBoardRepository.save(adminBoardDto.toEntity()).getBoardNum();
    }

    @Transactional
    public void updatePost(String changedContent, String writer, String song_title) {
        adminBoardRepository.updateKoreanLyric(changedContent, writer, song_title);
    }

    @Transactional
    public void deletePost(Long boardNum) {
        adminBoardRepository.deleteById(boardNum);
    }

}

