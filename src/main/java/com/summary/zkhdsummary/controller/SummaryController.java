package com.summary.zkhdsummary.controller;


import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import com.summary.zkhdsummary.bean.Comment;
import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.LogBean;
import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.service.LogService;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class SummaryController {
    @Autowired
    private LogService logService;

    @Autowired
    private LogService logService;

    /**
     * 访问首页 列表展示
     * @return  index
     */
    @RequestMapping(value = {"/","/index"})
    public String index(Model model,HttpServletRequest httpServletRequest){
        //获取到所有的user记录
        List<LogBean> list = logService.findList();
        model.addAttribute("users",list);
        //如果request域中有查询到的数据就将数据存入model 没有就不存入还用原来查出的默认数据
        if (httpServletRequest.getAttribute("searchUsers") != null){
            model.addAttribute("users",httpServletRequest.getAttribute("searchUsers"));
        }
        return "index";
    }

    /**
     * 根据 名字,总结时间进行搜索展示
     * @return  index
     */
    @GetMapping(value = {"/summary/search"})
    public String searchindex(@RequestParam(value = "username",required = false,defaultValue = "") String username, @RequestParam(value = "userdate",required = false,defaultValue = "") String userdate , Model model, HttpServletRequest
                              httpServletRequest){
        List<LogBean> logBeans = logService.searchLog(username, userdate);
        //向request域中添加查询到的数据
        if(logBeans!=null){
            httpServletRequest.setAttribute("searchUsers",logBeans);
        }
        //如果没查到数据没空就返回首页
        if (logBeans.size()==0){
            return "redirect:/index";
        }
        return "forward:/index";
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
