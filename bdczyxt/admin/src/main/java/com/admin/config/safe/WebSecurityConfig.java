package com.admin.config.safe;

import com.admin.security.SecurityUser;
import com.admin.security.UrlSecurityInterceptor;
import com.admin.security.UserDetailServiceImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@Log4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

//    @Value("${remember.key}")
    @Value("?ldfdr@@@1421AQ.,")
    private String key;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //处理自定义的权限
        http.addFilterAt(urlSecurityInterceptor(), FilterSecurityInterceptor.class);
        //设置记住我
        http.rememberMe()
                .userDetailsService(userDetailService)
                //用来防止修改token的一个key
                .key(key)
                //记住我有效时长为7天
                .tokenValiditySeconds(3600* 24 * 7)
                .useSecureCookie(true)
                .alwaysRemember(false);
        http.authorizeRequests().
                antMatchers("/static/**").permitAll().anyRequest().authenticated().
                and().formLogin().loginPage("/login").defaultSuccessUrl("/")
                .permitAll().successHandler(new AuthenticationSuccessHandler()).
                and().logout().permitAll().invalidateHttpSession(true).
                deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler).
                and().sessionManagement().maximumSessions(10).expiredUrl("/login");
    }
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/bootstrap/**","/dist/**","/jquery-ztree/**","/js/**"
        ,"/plugins/**","/ueditor/**");
    }
    @Bean
    protected AccessDecisionManager accessDecisionManager() {
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("");
        List voters = new ArrayList<>();
        voters.add(roleVoter);
        AccessDecisionManager accessDecisionManager = new AffirmativeBased(voters);
        return accessDecisionManager;
    }

    @Bean
    public UrlSecurityInterceptor urlSecurityInterceptor(){
        return new UrlSecurityInterceptor();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
        auth.eraseCredentials(false);
    }

   /* @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.md5((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.md5((String)rawPassword));
            }
        };
    }*/

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            try {
                SecurityUser user = (SecurityUser) authentication.getPrincipal();
                log.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
            } catch (Exception e) {
                log.info("LOGOUT EXCEPTION , e : " + e.getMessage());
            }
            httpServletResponse.sendRedirect("/login");
    };
    }


    private class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authentication)
                throws ServletException, IOException {
            clearAuthenticationAttributes(request);
            User userDetails = (User) authentication.getPrincipal();
            log.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }


}