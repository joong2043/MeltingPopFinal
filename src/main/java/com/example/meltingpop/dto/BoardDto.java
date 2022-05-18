package com.example.meltingpop.dto;


import com.example.meltingpop.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private Long boardNum;
    private String boardTitle;
    private String boardWriter;
    private String boardContent;
    private LocalDateTime boardCreatedDate;
    private LocalDateTime boardModifiedDate;

    // dto -> entity로 -> 데이터에 저장할 때(등록) 주로 사용
    public Board toEntity(){
        Board BoardBuild = Board.builder()
                .boardNum(boardNum)
                .title(boardTitle)
                .writer(boardWriter)
                .content(boardContent)
                .createdDate(boardCreatedDate)
                .modifiedDate(boardModifiedDate)
                .build();
        return BoardBuild;
    }

    @Builder
    public BoardDto(Long boardNum, String boardTitle, String boardWriter, String boardContent, LocalDateTime boardCreatedDate, LocalDateTime boardModifiedDate){
        this.boardNum = boardNum;
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardContent = boardContent;
        this.boardCreatedDate = boardCreatedDate;
        this.boardModifiedDate = boardModifiedDate;
    }
}
