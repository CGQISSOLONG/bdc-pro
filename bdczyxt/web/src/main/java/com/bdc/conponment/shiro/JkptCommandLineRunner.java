package com.bdc.conponment.shiro;

import com.bdc.common.BdcProtection;
import com.bdc.common.Constants;

import com.bdc.common.PassLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName 类名：JkptCommandLineRunner
 * @Description 功能说明：命令行运行类
 */
@Component
public class JkptCommandLineRunner implements CommandLineRunner {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Value("${controller.scan-package}")
    private String scanPackage;
    @Value("${server.context-path}")
    private String contextPath;

    @Override
    public void run(String... args) throws Exception {
        doScanner();
        Set<String> urlAndMethodSet = new HashSet<>();
        for (String aClassName : Constants.METHOD_URL_SET) {
            Class<?> clazz = Class.forName(aClassName);
            String baseUrl = "";
            String[] classUrl = {};

            if (clazz.getAnnotation(RequestMapping.class) != null) {
                classUrl = clazz.getAnnotation(RequestMapping.class).value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                //类上注解免登或者方法免登
                if (clazz.isAnnotationPresent(PassLogin.class) || method.isAnnotationPresent(PassLogin.class)
                        || clazz.isAnnotationPresent(BdcProtection.class) || method.isAnnotationPresent(BdcProtection.class)
                        ) {
                    String[] methodUrl = null;
                    StringBuilder sb = new StringBuilder();
                    if (method.getAnnotation(PostMapping.class) != null) {
                        methodUrl = method.getAnnotation(PostMapping.class).value();
                        if (methodUrl == null) {
                            methodUrl = method.getAnnotation(PostMapping.class).path();
                        }
                        baseUrl = getRequestUrl(classUrl, methodUrl, sb, "POST");
                    } else if (method.getAnnotation(GetMapping.class) != null) {
                        methodUrl = method.getAnnotation(GetMapping.class).value();
                        if (methodUrl == null) {
                            methodUrl = method.getAnnotation(GetMapping.class).path();
                        }
                        baseUrl = getRequestUrl(classUrl, methodUrl, sb, "GET");
                    } else if (method.getAnnotation(DeleteMapping.class) != null) {
                        methodUrl = method.getAnnotation(DeleteMapping.class).value();
                        if (methodUrl == null) {
                            methodUrl = method.getAnnotation(DeleteMapping.class).path();
                        }
                        baseUrl = getRequestUrl(classUrl, methodUrl, sb, "DELETE");
                    } else if (method.getAnnotation(PutMapping.class) != null) {
                        methodUrl = method.getAnnotation(PutMapping.class).value();
                        if (methodUrl != null) {
                            methodUrl = method.getAnnotation(PutMapping.class).path();
                        }
                        baseUrl = getRequestUrl(classUrl, methodUrl, sb, "PUT");
                    } else {
                        if (method.getAnnotation(RequestMapping.class) != null) {
                            methodUrl = method.getAnnotation(RequestMapping.class).value();
                            baseUrl = getRequestUrl(classUrl, methodUrl, sb, RequestMapping.class.getSimpleName());
                        }

                    }
                    if (baseUrl != null) {
                        urlAndMethodSet.add(baseUrl);
                    }
                }
            }

        }
        //System.out.println(urlAndMethodSet);
        Constants.METHOD_URL_SET = urlAndMethodSet;
    }

    private String getRequestUrl(String[] classUrl, String[] methodUrl, StringBuilder sb, String requestName) {
        sb.append(contextPath);
        if (classUrl != null) {
            for (String url : classUrl) {
                sb.append(url + "/");
            }
        }
        for (String url : methodUrl) {
            sb.append(url);
        }
        if (sb.toString().endsWith("/")) {
            sb.deleteCharAt(sb.length() - 1);
        }
//        System.out.println(sb.toString());
//        System.out.println(sb.toString().replaceAll("/+", "/") + ":--:" + requestName);
        return sb.toString().replaceAll("/+", "/") + ":--:" + requestName;
    }

    private void doScanner() {
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            HandlerMethod method = m.getValue();
            // 获取类型声明
            final String name = method.getMethod().getDeclaringClass().getName();
            if (name.startsWith(scanPackage) && !name.startsWith(scanPackage + ".test")) {
                Constants.METHOD_URL_SET.add(name);
            }
        }
    }
}
