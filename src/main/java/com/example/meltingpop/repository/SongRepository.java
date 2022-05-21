package com.example.meltingpop.repository;

import com.example.meltingpop.entity.Song_Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song_Info, Long> {

    @Query("select u from Song_Info u where u.song=:song")
    Song_Info selectSongInfo(@Param("song")String song);
}
