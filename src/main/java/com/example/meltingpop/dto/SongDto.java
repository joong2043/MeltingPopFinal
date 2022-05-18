package com.example.meltingpop.dto;

import com.example.meltingpop.entity.Song_Info;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {
    private Long songNum;
    private String song;
    private String singer;
    private String lyric;
    private String koreanLyric;

    @Builder
    public SongDto(Long songNum, String song, String singer, String lyric, String koreanLyric) {
        this.songNum = songNum;
        this.song = song;
        this.singer = singer;
        this.lyric = lyric;
        this.koreanLyric = koreanLyric;
    }

    public Song_Info toEntity(){
        Song_Info songbuild =  Song_Info.builder()
                .songNum(songNum)
                .song(song)
                .singer(singer)
                .lyric(lyric)
                .koreanLyric(koreanLyric)
                .build();

        return songbuild;
    }

}
