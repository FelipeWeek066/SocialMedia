package com.Graimy.SocialMedia.configs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping("/home")
    public String index() {
        return "index"; 
    }
    
    @GetMapping("/login")
    public String outro() {
        return "login"; 
    }
}