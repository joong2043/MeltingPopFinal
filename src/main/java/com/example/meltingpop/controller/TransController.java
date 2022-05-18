package com.example.meltingpop.controller;

import com.example.meltingpop.dto.SongDto;
import com.example.meltingpop.entity.Song_Info;
import com.example.meltingpop.papago.PapagoAPI;
import com.example.meltingpop.repository.TransRepository;
import com.example.meltingpop.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransController {
    @Autowired
    private TransService transService;

    @Autowired
    private TransRepository transRepository;

    private PapagoAPI papagoAPI;

    public TransController(PapagoAPI papagoAPI){
        this.papagoAPI = papagoAPI;
    }

    public Object korean;

    @PutMapping(value = "/trans")
    public String trans(@Param("transSong") String transSong){

        //System.out.println(song_infos);
        System.out.println(transSong);

        List<Song_Info> data = transRepository.findBySong(transSong);

        String englishLyric = data.get(0).getLyric();

        korean = papagoAPI.getKorean(englishLyric);

        transService.englishToKorean(korean,transSong);

        // System.out.println("번역된 단어 : " + korean);
        return "static/index";
    }
}
