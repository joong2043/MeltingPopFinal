package com.example.meltingpop.service;

import com.example.meltingpop.dto.UserDto;
import com.example.meltingpop.entity.User;
import com.example.meltingpop.repository.UserRepository;
import org.springframework.stereotype.Service;

// 비즈니스 로직 담당하는 서비스 클래스
@Service
public class UserService {
    // UserRepository 객체 생성
    UserRepository userRepository;

    // UserRepository 의존성을 UserService에 주입
    public UserService (UserRepository userRepository){
        this.userRepository =  userRepository;
    }

    // 회원 가입 관련 메소드 생성하자
    public void join(UserDto userDto){
        // JoinController를 통해 url 접속만해도 DB에 데이터가 저장되어서
        // if(user!=null) 조건문을 붙혔는데, user는 자동으로 생성되는(userNum 때문인 거 같음) 구조라서
        // user.getUserId!=null 조건문을 이용
        if (userDto.getUserId()!=null) {
            userRepository.save(userDto.toEntity());
        }
    }

    // User 엔티티에 있는 데이터 select 해보기
    public User login(String userEmail, String userPw){
        User user = userRepository.selectUserInfo(userEmail, userPw);
        return user;
    }
}
