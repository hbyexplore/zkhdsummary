package com.summary.zkhdsummary.controller;

import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author admin
 */
@Controller
public class LoginController{
    @Autowired
    private UserService userService;
    /**
     * 登录功能,登录成功后自动跳转到首页
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/loginPage")
    public String login(String username,String password) throws Exception {
        return "login";
    }

    /**
     * 注册 注册成功后跳转到登录页面
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user) throws Exception {
        //判断是否提交数据
        if(user.getName() != null){
            //根据名字查重复
            User userIdByName = userService.findUserIdByName(user.getName());
          if (userIdByName!=null) {
              if (userIdByName.getId() != null) {
                  return "register";
              }
          }
            userService.insterUser(user);
            return "redirect:/loginPage";
        }

        return "register";
    }




}
