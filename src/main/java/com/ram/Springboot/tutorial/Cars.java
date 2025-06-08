package com.ram.Springboot.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cars {
    @Autowired
    private Toyota t;

    @GetMapping("/toyota")
    public String carName()
    {
        return t.carNames();
    }

}
