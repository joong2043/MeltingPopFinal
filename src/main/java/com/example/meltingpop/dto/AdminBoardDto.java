package com.example.meltingpop.dto;

import com.example.meltingpop.entity.AdminBoard;
import com.example.meltingpop.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class AdminBoardDto {
    private Long boardNum;
    private String boardTitle;
    private String boardWriter;
    private String koreanLyric;
    private String englishLyric;
    private String singer;
    private LocalDateTime boardCreatedDate;

    // dto -> entity로 -> 데이터에 저장할 때(등록) 주로 사용
    public AdminBoard toEntity(){
        AdminBoard AdminBoardBuild = AdminBoard.builder()
                .boardNum(boardNum)
                .songtitle(boardTitle)
                .singer(singer)
                .writer(boardWriter)
                .korean_lyric(koreanLyric)
                .english_lyric(englishLyric)
                .createdDate(boardCreatedDate)
                .build();
        return AdminBoardBuild;
    }

    @Builder
    public AdminBoardDto(Long boardNum, String singer, String boardTitle, String boardWriter, String englishLyric, String koreanLyric, LocalDateTime boardCreatedDate){
        this.boardNum = boardNum;
        this.boardTitle = boardTitle;
        this.singer = singer;
        this.boardWriter = boardWriter;
        this.englishLyric = englishLyric;
        this.boardCreatedDate = boardCreatedDate;
        this.koreanLyric = koreanLyric;
    }
}
