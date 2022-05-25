package com.example.meltingpop.repository;

import com.example.meltingpop.entity.AdminBoard;
import com.example.meltingpop.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminBoardRepository extends JpaRepository<AdminBoard, Long> {
    
    @Query("select u from AdminBoard u where u.songtitle=:song_title")
    List<AdminBoard> findBySongtitle(@Param("song_title")String song_title);

    @Modifying
    @Query("update AdminBoard u set u.korean_lyric =:changedLyric, u.writer =:writer where u.songtitle=:song_title")
    Integer updateKoreanLyric(@Param("changedLyric")String changedContent, @Param("writer")String writer, @Param("song_title")String song_title);

}
