package com.example.meltingpop.service;

import com.example.meltingpop.dto.SongDto;
import com.example.meltingpop.entity.Song_Info;
import com.example.meltingpop.repository.TransRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransService {
    private TransRepository transRepository;

    public TransService(TransRepository transRepository){
        this.transRepository = transRepository;
    }

    @Transactional
    public void englishToKorean(Object korean, String song){
        transRepository.setKoreanLyricData(korean,song);
    }
    /*

    @Transactional
    public Song_Info getSonglist(){
        song_info = transRepository.getLyricData("off my face");

        List<SongDto> songDtoList = new ArrayList<>();

        for(Song_Info song_info:song_infos){
            SongDto songDto = SongDto.builder()
                    .songNum(song_info.getSongNum())
                    .song(song_info.getSong())
                    .singer(song_info.getSinger())
                    .singer(song_info.getSinger())
                    .lyric(song_info.getLyric())
                    .build();
            songDtoList.add(songDto);
        }
        return songDtoList;


        return song_info;
    }
    */
}
