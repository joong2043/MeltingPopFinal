package com.example.meltingpop.repository;

import com.example.meltingpop.dto.UserDto;
import com.example.meltingpop.entity.User;
import com.example.meltingpop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserJoinTest {

    @Autowired
    UserService userService;

    @Test
    public void joinUser() {
        UserDto user1 = UserDto.builder()
                .userId("id0")
                .userEmail("id0@naver.com")
                .userPw("0")
                .userAuthentication(0)
                .build();
        userService.join(user1);
    }

}
