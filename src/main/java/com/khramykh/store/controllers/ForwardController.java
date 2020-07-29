package com.khramykh.store.controllers;

import com.khramykh.store.domain.orgs.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class ForwardController {

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping(value = "/")
    public String forward(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        HashMap<Object, Object> frontendData = new HashMap<>();

        model.addAttribute("isDevMode", "dev".equals(profile));
        if(user != null) {
            frontendData.put("profile", user);
        }
        model.addAttribute("frontendData", frontendData);

        return "index";
    }
}
