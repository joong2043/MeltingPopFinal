package com.example.meltingpop.repository;

import com.example.meltingpop.dto.SongDto;
import com.example.meltingpop.entity.Song_Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransRepositoryTest {

    @Autowired
    TransRepository transRepository;

    private Song_Info saveKoreanLyric(){
        Song_Info song_info1 = new Song_Info();
        song_info1.setKoreanLyric("hello");
        Song_Info song_info2 = transRepository.save(song_info1);
        return song_info2;
    }

    @Test
    public void findLyricTest(){
        //given

        /*

        Song_Info song_info1 = Song_Info.builder()
                .singer("Joonghyun")
                .song("good4")
                .lyric("hi everyone")
                .build();
        transRepository.save(song_info1);


         */

        List<Song_Info> data = transRepository.findBySong("good4");

        String Lyric = data.get(0).getLyric();
        //List<Song_Info> data1 = transRepository.setKoreanLyricData("good","good3");

        transRepository.setKoreanLyricData("hello1","good3");
        List<Song_Info> song_info1 = transRepository.findBySong("good3");

        assertThat(song_info1.get(0).getKoreanLyric()).isEqualTo("hello1");

        //System.out.println(data.get(0).getKoreanLyric());

    }

}
