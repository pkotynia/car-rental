package com.sda.carrental.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello/marcin")
public class HelloMarcinControler {

    @GetMapping
    public String hello() {
        return "Hello Marcin";

    }





}
