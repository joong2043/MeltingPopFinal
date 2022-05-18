package com.example.meltingpop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// 데이터베이스에 연결하여 테이블 생성을 위해 필요한 클래스
// 밑에 @Column을 붙여서 만들고 싶은 속성을 명시하면 된다.
// @Entity는 JPA가 관리하는 클래스로, 해당 클래스를 엔티티라고 한다.
//
@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class User {
    // 기본키
    // 회원가입 순서대로 UserNum : 1,2,3,....
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNum;

    @Column
    private String userId;

    @Column
    private String userPw;

    @Column
    private String userEmail;

    @Column
    private Integer userAuthentication;

    @Column
    private String userLyricChange;

    @Builder
    public User(Long userNum, String userId, String userPw, String userEmail, Integer userAuthentication, String userLyricChange){
        this.userNum = userNum;
        this.userId = userId;
        this.userPw = userPw;
        this.userEmail = userEmail;
        this.userAuthentication = userAuthentication;
        this.userLyricChange = userLyricChange;

    }

}