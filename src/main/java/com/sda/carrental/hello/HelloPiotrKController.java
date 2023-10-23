package com.sda.carrental.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello/piotr")
public class HelloPiotrKController {

    @GetMapping
    public String hello() {
        return "Hello Piotrek";
    }

}
