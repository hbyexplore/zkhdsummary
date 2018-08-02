package com.summary.zkhdsummary.controller;


import com.github.pagehelper.PageInfo;
import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.config.PageBean;
import com.summary.zkhdsummary.config.SummarySecurity;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import com.summary.zkhdsummary.bean.LogBean;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class SummaryController {
    @Autowired
    private LogService logService;
    @Autowired
    private ScheduTask scheduTask;
    /**
     * 访问首页 列表展示
     *
     * @return index
     */
    @RequestMapping(value = {"/", "/index"})
    public String index(Model model, HttpServletRequest httpServletRequest) {
        //获取到所有的user记录
        List<LogBean> list = logService.findList();
        List<String> objects = scheduTask.users;
        model.addAttribute("users", list);
        //如果request域中有查询到的数据就将数据存入model 没有就不存入还用原来查出的默认数据
        if (httpServletRequest.getAttribute("searchUsers") != null) {
            model.addAttribute("users", httpServletRequest.getAttribute("searchUsers"));
        }
        if(objects != null && objects.size() != 0){
            model.addAttribute("nameList",objects);
        }
        return "index";
    }

    /**
     * 根据 名字,总结时间进行搜索展示
     *
     * @return index
     */
    @GetMapping(value = {"/summary/search"})
    public String searchindex(@RequestParam(value = "username", required = false, defaultValue = "") String username, @RequestParam(value = "userdate", required = false, defaultValue = "") String userdate, Model model, HttpServletRequest
            httpServletRequest) {
        List<LogBean> logBeans = logService.searchLog(username, userdate);
        //向request域中添加查询到的数据
        if(logBeans!=null){
            httpServletRequest.setAttribute("searchUsers",logBeans);
        }
        //如果没查到数据没空就返回首页
        if (logBeans.size() == 0) {
            return "redirect:/index";
        }
        return "forward:/index";
    }

    /**
     * 添加每日总结
     *
     * @return add.html
     */
    @GetMapping(value = {"/summary/addSummary/{name}"})
    public String searchindessx(
            @PathVariable(name = "name") String username,
            @RequestParam(required = false, value = "title", defaultValue = "") String logTitle,
            @RequestParam(required = false, value = "content", defaultValue = "") String logContent) {
        //没有写总结就跳到add页面
        if (logTitle.equals("") && logContent.equals("")){
            return "add";
        }
        //写了总结就存储总结返回个人中心页面
        logService.addSummary(username,logTitle,logContent);
        return "personal";
        }

    /**
     * 详情页展示
     * @return detail
     */
    @RequestMapping("/summary/detail/{id}")
    public String detail(@PathVariable() int id, Model model){
        Log log = logService.findLogById(id);
        model.addAttribute("log",log);
        return "detail";
    }

    /***
     * 跳转到个人中心页面,展示出该用户所有的总结
     * @return
     */
    @RequestMapping("/summary/personal/{username}")
    public String personal(Model model,HttpServletRequest request,
                           @PathVariable String username,
                           @RequestParam(required = false, value = "currement", defaultValue = "") int currement,
                           @RequestParam(required = false, value = "pageSize", defaultValue = "") int pageSize
    ){
        //根据名称查询出该用户所有相关的评论
        //将信息封装到一个list集合中
        PageBean<Log> pageBean = logService.findLogById(username,currement,pageSize);
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
