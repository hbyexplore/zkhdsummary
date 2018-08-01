package com.summary.zkhdsummary.controller;


import com.summary.zkhdsummary.bean.Comment;
import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class SummaryController {
    @Autowired
    private LogService logService;

    /**
     * 访问首页 列表展示
     * @return  index
     */
    @RequestMapping(value = {"/","/index"})
    public String index(Model model){
        //获取到所有的user记录
        List<User> list = logService.findList();
        //获取到发表总结的时间
        List<User> listTime = logService.findTime(list);
        //获取每个用户最新总结的评论条数返回的是所有的总结
        List<List<Log>> listSummary = logService.findSummary(listTime);
        for (List<Log> logs : listSummary) {
            for (Log log : logs) {
                //获取总结的评论
                List<Comment> comment = log.getComment();
                //存储每个总结对应的评论条数
                log.setCommentCount(comment.size());
                for (User user : listTime) {
                    //如果 总结是对应user那就将对应的条数放入user存储
                    if (log.getUserid().equals(user.getId())){
                        user.setCommentCount(log.getCommentCount());
                    }
                }
            }
        }
        model.addAttribute("users",listTime);
        return "index";
    }
}