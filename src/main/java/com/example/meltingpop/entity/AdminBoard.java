package com.example.meltingpop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class AdminBoard {
    // 기본키 자동으로 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;

    @Column
    private String songtitle;

    @Column
    private String singer;

    @Column
    private String writer;

    @Column(length = 5000)
    private String english_lyric;

    @Column(length = 5000)
    private String korean_lyric;

    @Column
    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public AdminBoard(Long boardNum, String songtitle, String singer, String writer, String korean_lyric, String english_lyric, LocalDateTime createdDate){
        this.boardNum = boardNum;
        this.songtitle = songtitle;
        this.singer = singer;
        this.writer = writer;
        this.korean_lyric = korean_lyric;
        this.createdDate = createdDate;
        this.english_lyric = english_lyric;
    }

}
