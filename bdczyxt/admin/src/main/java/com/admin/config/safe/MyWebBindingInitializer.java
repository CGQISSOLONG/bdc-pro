package com.admin.config.safe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * @ClassName 类名：MyWebBindingInitializer
 * @Description 功能说明：防止xss攻击
 */
@Configuration
public class MyWebBindingInitializer implements WebBindingInitializer {

    private static final WebBindingInitializer webBindingInitializer = new MyWebBindingInitializer();


    @Autowired
    public void getWebBindingInitializer(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        requestMappingHandlerAdapter.setWebBindingInitializer(webBindingInitializer);
    }

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true,
                true, false));
    }
}
