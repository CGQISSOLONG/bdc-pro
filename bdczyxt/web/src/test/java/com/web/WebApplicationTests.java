package com.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.Request;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class WebApplicationTests {

    @Test
    void contextLoads() {
        final Date loginTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dates = simpleDateFormat.format(loginTime);
        System.out.println(dates);
    }

}
