package com.example.springboot.web;


import com.example.springboot.dto.HelloResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto (@RequestParam(name = "name")String name, @RequestParam(name = "amount")int amount) {
        return new HelloResponseDto(name, amount);
    }
}
