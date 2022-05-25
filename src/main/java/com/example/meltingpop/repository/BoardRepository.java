package com.example.meltingpop.repository;

import com.example.meltingpop.dto.BoardDto;
import com.example.meltingpop.entity.Board;
import com.example.meltingpop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Long은 Board 엔티티의 기본키의 데이터타입으로 명시해줘야 한다.
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    //MySQL에 있는 Board 데이터에 대한 접근이 필요하면 작성

    //List<BoardDto> findByBoardTitleAndWriter(String boardTitle,String boardWriter);


    @Query("select u from Board u where u.songtitle=:song_title")
    List<Board> findBySongtitle(@Param("song_title")String song_title);

    @Query("select u from Board u where u.songtitle=:song_title and u.writer=:writer")
    List<Board> findBySongtitleandwriter(@Param("song_title")String song_title, @Param("writer")String writer);

    @Modifying
    @Query("update Board u set u.korean_lyric =:changedLyric where u.songtitle=:song_title")
    Integer updateKoreanLyric(@Param("changedLyric")String changedContent, @Param("song_title")String song_title);



}

