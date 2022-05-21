package com.example.meltingpop.service;

import com.example.meltingpop.entity.Song_Info;
import com.example.meltingpop.repository.SongRepository;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private SongRepository songRepository;

    public SongService(SongRepository songRepository){
        this.songRepository=songRepository;
    }

    public Song_Info getSongData(String song){
        Song_Info song_info = songRepository.selectSongInfo(song);
        return song_info;
    }
}
