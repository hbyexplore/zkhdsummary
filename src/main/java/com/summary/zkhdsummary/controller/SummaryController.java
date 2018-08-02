package com.summary.zkhdsummary.controller;


import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SummaryController {

    @Autowired
    private LogService logService;

    /**
     * 访问首页
     * @return
     */
    @RequestMapping(value = {"/index","/"})
    public String index(){

        return "index";
    }
    @RequestMapping(value = {"/summary/add"})
    public String indexx(){

        return "add";
    }


    /**
     * 详情页展示
     * @return
     */
    @RequestMapping("/summary/detail/{id}")
    public String detail(@PathVariable int id, Model model){

        Log log = logService.findLogById(id);

        model.addAttribute("log",log);

        //System.out.println(log);

        return "detail";
    }
}
