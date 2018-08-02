package com.summary.zkhdsummary.controller;


import com.github.pagehelper.PageInfo;
import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.config.PageBean;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SummaryController {

    @Autowired
    private LogService logService;
    @Autowired
    private ScheduTask scheduTask;
    /**
     * 访问首页
     * @return
     */
    @RequestMapping(value = {"/","/index"})
    public String index(Model model){
       // scheduTask.clear();
        List<String> objects = scheduTask.users;
        if(objects != null && objects.size() != 0){
            model.addAttribute("nameList",objects);
        }
        return "index";
    }
    @RequestMapping(value = {"/summary/add"})
    public String indexx(){
        return "login";
    }

    /***
     * 跳转到个人中心页面,展示出该用户所有的总结
     * @return
     */
    @RequestMapping("/summary/persona")
    public String personal(Model model,HttpServletRequest request){
        //获取登录用户的名称
        String name = request.getParameter("id");
        String currement = request.getParameter("currement");
        String pageSize = request.getParameter("pageSize");
        //将字符串转为基本数据类型
        Integer id = Integer.parseInt(name);
        Integer currement1 = Integer.parseInt(currement);
        Integer pageSize1 = Integer.parseInt(pageSize);
        //根据名称查询出该用户所有相关的评论
        //将信息封装到一个list集合中
        PageBean<Log> pageBean = logService.findLogById(id,currement1,pageSize1);
        List<Log> logList = pageBean.getItems();
        PageInfo pageInfo = new PageInfo(logList);
        System.out.println(logList);
        model.addAttribute("logList",logList);
        model.addAttribute("pageBean",pageBean);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "personal";
    }
}
