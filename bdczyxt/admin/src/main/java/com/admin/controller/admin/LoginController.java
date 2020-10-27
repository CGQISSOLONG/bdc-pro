package com.admin.controller.admin;


import com.bdc.util.UrlConstant;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j
public class LoginController {

    @GetMapping(value = UrlConstant.LOGIN)
    public String login() {
        return "login";
    }
}
