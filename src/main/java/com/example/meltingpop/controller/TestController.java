package com.example.meltingpop.controller;

import com.example.meltingpop.entity.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/test/ajax.html")

    public Map<String, Object> testAjax(TestVO testVO){

        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println(testVO.getName());
        System.out.println(testVO.getSex());
        System.out.println(testVO.getAge());
        System.out.println(testVO.getTellPh());

        result.put("code","0000");

        return result;
    }
}
