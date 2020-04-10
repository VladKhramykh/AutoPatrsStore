package com.khramykh.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardController {

    @GetMapping(value = "/")
    public String forward(
    ) {
        return "index";
    }
}
