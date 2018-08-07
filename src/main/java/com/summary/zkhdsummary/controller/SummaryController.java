package com.summary.zkhdsummary.controller;


import com.github.pagehelper.PageInfo;
import com.summary.zkhdsummary.bean.*;
import com.summary.zkhdsummary.config.PageBean;
import com.summary.zkhdsummary.service.CommentService;
import com.summary.zkhdsummary.service.LogService;
import com.summary.zkhdsummary.service.SubmitService;
import com.summary.zkhdsummary.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class SummaryController {
    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @Autowired
    private ScheduTask scheduTask;

    @Autowired
    private SubmitService submitService;
    /**
     * 访问首页 列表展示
     *
     * @return index
     */
    @RequestMapping(value = {"/","/index"})
    public String index(Model model, HttpServletRequest httpServletRequest,
                        @RequestParam(value = "username", required = false, defaultValue = "") String username,
                        @RequestParam(value = "userdate", required = false, defaultValue = "") String userdate,
                        @RequestParam(required = false, value = "currement", defaultValue = "1") int currement,
                        @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws ParseException {
        //如果request域中有查询到的数据就将数据存入model 没有就不存入还用原来查出的默认数据
        if (httpServletRequest.getAttribute("searchUsers") != null) {
            model.addAttribute("users" , httpServletRequest.getAttribute("searchUsers"));
            return "index";
        }else {
            //获取到所有的user记录
            PageBean<LogBean> pageBean = logService.searchLog(username,userdate,currement,pageSize);
            List<LogBean> items = pageBean.getItems();
            PageInfo pageInfo = new PageInfo(items);

            model.addAttribute("pageBean",pageBean);
            model.addAttribute("users",items);
            //获得当前页
            model.addAttribute("pageNum", pageInfo.getPageNum());
            //获得一页显示的条数
            model.addAttribute("pageSize", pageSize);
            //是否是第一页
            model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
            //获得总页数
            model.addAttribute("totalPages", pageInfo.getPages());
            //是否是最后一页
            model.addAttribute("isLastPage", pageInfo.isIsLastPage());

            model.addAttribute("username", username);

            model.addAttribute("userdate", userdate);
        }

        Set<String> userList = new LinkedHashSet<>();
        //定时器
        List<String> objects = scheduTask.users;

        if(objects == null || objects.size() == 0){
            //在将全部的数据查询出来
            List<NoSubmiat> all = submitService.findAll();
            for(int K = 0 ; K< all.size() ; K++){
                NoSubmiat noSubmiat = all.get(K);
                userList.add(noSubmiat.getUnames());

            }
        }else{

        for(int i = 0 ; i<objects.size() ; i++){
            String s = objects.get(i);
            //将当天没有提交总结的员工存入到数据库中
           // submitService.insert(s);
            //在将全部的数据查询出来
//             List<NoSubmiat> all = submitService.findAll();
//            for(int K = 0 ; K< all.size() ; K++){
//                NoSubmiat noSubmiat = all.get(K);
            userList.add(s);
//        }
    }
}

        //获取当天时间的时分
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd 17:31:00");
        String date = simp.format(new Date());
        Date parse = simp.parse(date);
        Calendar c = Calendar.getInstance();
        c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        long time1 = c.getTimeInMillis();
        SimpleDateFormat simp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = System.currentTimeMillis();
        if(time < time1){
            SimpleDateFormat simp2 = new SimpleDateFormat("yyyy-MM-dd");
            //获取当天时间的前一天
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            parse = calendar.getTime();
            String format = simp2.format(parse);
            model.addAttribute("showTime",format);
        }else if(time > time1){
            SimpleDateFormat simp3 = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("showTime",simp3.format(new Date()));
        }

        //定时器
        model.addAttribute("nameList",userList);
        System.out.println(userList);
        return "index";
    }
    /**
     * 根据 名字,总结时间进行搜索展示
     *
     * @return index
     */
    @GetMapping(value = {"/summary/search"})
    public String searchindex(@RequestParam(value = "username", required = false, defaultValue = "") String username, @RequestParam(value = "userdate", required = false, defaultValue = "") String userdate,
                              @RequestParam(required = false, value = "currement",defaultValue = "1") int currement,
                              @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,Model model, HttpServletRequest
            httpServletRequest) {
        //查出需要分页的pageBean
        PageBean<LogBean> pageBean = logService.searchLog(username, userdate,currement,pageSize);
        List<LogBean> logBeans = pageBean.getItems();
        PageInfo pageInfo = new PageInfo(logBeans);
        //向request域中添加查询到的数据
        if(logBeans!=null){
            httpServletRequest.setAttribute("searchUsers",logBeans);
            model.addAttribute("pageBean",pageBean);
            //获得当前页
            model.addAttribute("pageNum", pageInfo.getPageNum());
            //获得一页显示的条数
            model.addAttribute("pageSize", pageSize);
            //是否是第一页
            model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
            //获得总页数
            model.addAttribute("totalPages", pageInfo.getPages());
            //是否是最后一页
            model.addAttribute("isLastPage", pageInfo.isIsLastPage());

            model.addAttribute("username", username);

            model.addAttribute("userdate", userdate);
        }
        //如果没查到数据没空就返回首页
        if (logBeans.size() == 0) {
            return "redirect:/";
        }
        return "forward:/";
    }

    /**
     * 添加每日总结
     *
     * @return add.html
     */
    @GetMapping(value = {"/summary/addSummary/{username}"})
    public String searchindx(@PathVariable String username,String logTitle,String logContent) throws UnsupportedEncodingException {
        if ("anonymousUser".equals(username)){
            return "redirect:/";
        }
        //没有写总结就跳到add页面
        if (logTitle==null && logContent==null){
            return "add";
        }
        //写了总结就存储总结返回个人中心页面
        logService.addSummary(username,logTitle,logContent);
        return "redirect:/summary/personal?username="+new String(username.getBytes(),"iso8859-1");
        }

    /**
     * 详情页展示
     * @return detail
     */
    @RequestMapping("/summary/detail/{id}")
    public String detail(@PathVariable() int id, Model model){
        //根据id查出日志
        Log log = logService.findLogById(id);

        log.setId(id);

        model.addAttribute("log",log);
        //根据日志id查出评论
        List<Detail> detailById = commentService.findDetailById(id);

        model.addAttribute("detail",detailById);

        //根据热度查出前8条
        List<Log> LogByHot = logService.findAllByHot();

        model.addAttribute("LogByHot",LogByHot);

        //查出评论数最多的前8条
        List<LogBean> LogBeans = logService.findAllByCommentTotal();

        model.addAttribute("LogBeans",LogBeans);
        return "detail";
    }

    /***
     * 跳转到个人中心页面,展示出该用户所有的总结
     * @return
     */
    @RequestMapping("/summary/personal")
    public String personal(Model model,HttpServletRequest request,
                           @RequestParam(required = false, value = "username", defaultValue = "") String username,
                           @RequestParam(required = false, value = "currement", defaultValue = "1") int currement,
                           @RequestParam(required = false, value = "pageSize", defaultValue = "5") int pageSize,
                           @RequestParam(required = false, value = "id") Integer id
    ){
        //根据名称查询出该用户所有相关的评论
        //将信息封装到一个list集合中
        PageBean<Log> pageBean = new PageBean<>();
        if (!username.equals("")){
            User userId= userService.findUserIdByName(username);
            pageBean = logService.findLogById(userId.getId(),currement,pageSize);
            model.addAttribute("userId",userId.getId());
        }else {
             pageBean = logService.findLogById(id,currement,pageSize);
            model.addAttribute("userId",id);

        }
        List<Log> logList = pageBean.getItems();
        PageInfo pageInfo = new PageInfo(logList);
        model.addAttribute("logList",logList);
        model.addAttribute("pageBean",pageBean);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageSize);
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "personal";
    }

    /**
     * 提交评论 然后跳转回详情页
     * @return
     */
    @RequestMapping("/summary/insterComment/{log_id}/{userName}")
    public String insterComment(@PathVariable Integer log_id,@PathVariable String userName,String content){

        commentService.insterComment(log_id,userName,content);

        return "redirect:/summary/detail/"+log_id;
    }
}
