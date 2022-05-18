package com.example.meltingpop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Song_Info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long songNum;

    @Column(length = 100)
    private String song;

    @Column(length = 100)
    private String singer;

    @Column(length = 5000)
    private String lyric;

    @Column(length = 5000)
    private String koreanLyric;

    @Builder
    public Song_Info(Long songNum, String song, String singer, String lyric, String koreanLyric) {
        this.songNum = songNum;
        this.song = song;
        this.singer = singer;
        this.lyric = lyric;
        this.koreanLyric = koreanLyric;
    }
}