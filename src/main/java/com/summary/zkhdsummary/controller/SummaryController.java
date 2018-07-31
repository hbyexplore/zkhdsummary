package com.summary.zkhdsummary.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SummaryController {

    /**
     * 访问首页
     * @return
     */
    @RequestMapping(value = {"/","/index"})
    public String index(){

        return "index";
    }
    @RequestMapping(value = {"/summary/add"})
    public String indexx(){

        return "login";
    }
}
