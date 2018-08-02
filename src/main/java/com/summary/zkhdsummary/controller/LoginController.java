package com.summary.zkhdsummary.controller;

import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    /**
     * 登录功能,登录成功后自动跳转到首页
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/loginPage")
    public String login(String username,String password){

        return "login";
    }

    /**
     * 注册 注册成功后跳转到登录页面
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user){

        if(user.getName() != null){

            userService.insterUser(user);

            return "index";
        }

        return "register";
    }

}
