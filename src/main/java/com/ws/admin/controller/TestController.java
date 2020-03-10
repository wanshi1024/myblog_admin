package com.ws.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/aaa")
    public Object test1(){
        Map<String,String> map=new HashMap<>();
        map.put("name","9999");
        map.put("hh"," 777");
        List list = new ArrayList<>();
        list.add(map);
        return list;
    }
}
