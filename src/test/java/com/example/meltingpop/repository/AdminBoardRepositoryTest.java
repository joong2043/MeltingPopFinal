package com.example.meltingpop.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminBoardRepositoryTest {
    @Autowired
    AdminBoardRepository adminBoardRepository;

    @Test
    public void deleteAll(){
        adminBoardRepository.deleteAll();
    }
}
