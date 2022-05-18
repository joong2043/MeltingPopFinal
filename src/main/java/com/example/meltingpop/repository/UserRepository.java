package com.example.meltingpop.repository;

import com.example.meltingpop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//JpaRepository의 상속을 받으면
// UserRepository 인터페이스가
// Jpa에서 담당하는
// 저장, 검색, 수정, 삭제 등의 기능을 자유롭게 사용할 수 있다.

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userEmail=:userEmail and u.userPw=:userPw")
    User selectUserInfo(@Param("userEmail")String userEmail, @Param("userPw")String userPw);
}
