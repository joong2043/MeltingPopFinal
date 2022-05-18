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

    @Modifying
    @Query("update Board u set u.content =:changedContent where u.boardNum=:boardNum")
    Integer updateBoard(@Param("changedContent")String changedContent, @Param("boardNum")Long boardNum);

}

