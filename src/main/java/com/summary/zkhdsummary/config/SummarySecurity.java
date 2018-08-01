package com.summary.zkhdsummary.config;

import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
       /* //定制请求的授权规则规则
        http.authorizeRequests().antMatchers("/").permitAll()
                //请求level1,要有VIP1授权
                .antMatchers("/summary/**").hasRole("user")
        //提交的参数名默认为 username  password
                .and()
                //自定义登录页面
                .formLogin().loginPage("/login")
        //开启自动配置的注销功能,默认注销后跳转到登录页面
                .and()
                //自定义跳转到首页
                .logout().logoutSuccessUrl("/login")
        //开启记住我功能,参数名默认为rememberMe
                .and()
                .rememberMe();*/
    }

    /**
     * 定义认证规程
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* List<User> allUser = userService.findAllUser();
        for (User user : allUser) {
            System.out.println(
                    user
            );
        }
*/
    }
}
