package com.example.meltingpop.repository;

import com.example.meltingpop.dto.SongDto;
import com.example.meltingpop.entity.Song_Info;
import com.example.meltingpop.repository.mapping.SongLyricMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TransRepository extends JpaRepository<Song_Info, Long> {

    public List<Song_Info> findBySong(String song);

    @Modifying
    @Query("update Song_Info u set u.koreanLyric=:koreanLyric where u.song=:song")
    void setKoreanLyricData(@Param("koreanLyric")Object koreanLyric, @Param("song")String song);


    @Query("select u from Song_Info u where u.koreanLyric=:koreanLyric")
    List<Song_Info> selectSong(@Param("koreanLyric")String koreanLyric);
    /*
    @Query("select u.lyric from Song_Info u where u.song=:song")
    Song_Info getLyricData(@Param("song")String song);

 */

    //Optional<SongLyricMapping> findBySong(String song);
    //List<Song_Info> findBySong(String song);
}
