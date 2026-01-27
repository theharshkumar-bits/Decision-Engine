package com.example.decision_engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Myclass { 

    @GetMapping("/")
    public String sayhello(){
        return "<div><h1 style=\"color:blue; width:200px; margin:auto; \">Welcome<h1></div>";
    }
}
