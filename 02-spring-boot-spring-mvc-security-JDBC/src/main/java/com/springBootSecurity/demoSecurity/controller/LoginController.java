package com.springBootSecurity.demoSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")     //@Bean'den gelir.
    public String showMyLoginPage()
    {
        return "fancy-login";   //html sayfasıdır
    }

    //add request mapping for /access-denied
    @GetMapping("/access-denied")
    public String ShowaccessDenied()
    {
        return "access-denied";
    }

}
