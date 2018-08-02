package com.summary.zkhdsummary.config;

import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@EnableWebSecurity
public class SummarySecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    /**
     * 定制请求的授权规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许所有用户访问"login"和"/register"
        http
                .authorizeRequests()
                .antMatchers("/","/index","/loginPage","/register").permitAll()
                .antMatchers("/summary/**").hasRole("user");
                //.antMatchers("/summary/**").hasRole("vip");
        http
                .formLogin().loginPage("/loginPage");
        http
                .logout().logoutSuccessUrl("/loginPage");
    }

    /**
     * 定义认证规程
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        List<User> allUser = userService.findAllUser();

        for(User user : allUser){
            auth.inMemoryAuthentication()
                    .withUser(user.getName()).password(user.getPassword()).roles(user.getUserPower().getPower());


        }

    }

    /**
     * 加密算法
     * 在Security5 中会对提交的表单进行加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
