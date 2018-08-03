package com.summary.zkhdsummary.service.Impl;

import com.github.pagehelper.PageHelper;

import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.config.PageBean;
import com.summary.zkhdsummary.bean.LogBean;
import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.mapper.LogMapper;
import com.summary.zkhdsummary.mapper.UserMapper;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private UserMapper userMapper;

/**
 * 搜索
 * */
    @Override
    public PageBean<LogBean> searchLog(String username, String userdate, int currement, int pageSize) {
        PageHelper.startPage(currement,pageSize);
        List<LogBean> logBeans = logMapper.searchLog(username, userdate);
        for (LogBean logBean : logBeans) {
            Integer lid = logBean.getLid();
            //获取帖子对应的评论数
            Integer listById = logMapper.findListById(lid);
            logBean.setCommentCount(listById);
        }
        //获得总条数
        int searchLogCount=logMapper.getSearchLogCount(username,userdate);
        PageBean<LogBean> pageBean = new PageBean<>(currement,pageSize,searchLogCount);
        pageBean.setItems(logBeans);
        return pageBean;
    }
/**
 * 添加总结
 * */
    @Override
    public void addSummary(String username, String logTitle, String logContent) {
        //先根据用户名获取id
        List<User> allUser = userMapper.findAllUser();
        for (User user : allUser) {
            String name = user.getName();
            if (name.equals(username)){
                //将id以及总结标题和内容存入log表
                HashMap<String, Object> summaryMap = new HashMap<>();
                summaryMap.put("userId",user.getId());
                summaryMap.put("logTitle",logTitle);
                summaryMap.put("logContent",logContent);
                summaryMap.put("newTime",new Date());
                logMapper.insertSummary(summaryMap);
                break;
            }
        }

    }

    /**
     * 根据热度查询出前8条
     * @return
     */
    @Override
    public List<Log> findAllByHot() {
        return logMapper.findAllByHot();
    }

    /**
     * 查出评论最多的前8条
     * @return
     */
    @Override
    public List<LogBean> findAllByCommentTotal() {

        List<LogBean> logBeans = new ArrayList<>();
        //查出所有日志
        List<Log> logs = logMapper.finAll();
        //根据id查出每条日志的评论
        for(int i = 0; i<logs.size(); i++){
            LogBean logBean = logMapper.findList(logs.get(i).getId());
            //添加进集合
            logBeans.add(logBean);
        }

        //通过评论数排序,并截取前8条
        Collections.sort(logBeans, new Comparator<LogBean>() {
            @Override
            public int compare(LogBean o1, LogBean o2) {
                if(o1.getCommentCount() < o2.getCommentCount()){
                    return 1;
                }
                if(o1.getCommentCount() == o2.getCommentCount()){
                    return 0;
                }
                return -1;
            }
        });

        //截取前8条
        while (true){
            if(logBeans.size() > 8){
                logBeans.remove(8);
            }else {
                break;
            }
        }

        return logBeans;
    }

    /**
     * 根据id查log
     * @param id
     * @return
     */
    @Override
    public Log findLogById(int id) {

        Log log = logMapper.findLogById(id);
        return log;
    }

    /***
     * 查找登录用户所有总记录，并进行分页展示
     * @param id
     * @return
     */
    @Override
    public PageBean<Log> findLogById(Integer id,int currement,int pageSize) {
        PageHelper.startPage(currement,pageSize);
        //查询全部内容
        List<Log> logList = logMapper.findAllLogById(id);
        //查询总条数
        int countLog = logMapper.countLog(id);
        PageBean<Log> pageBean = new PageBean<>(currement,pageSize,countLog);
        pageBean.setItems(logList);
        return pageBean;
    }

    @Override
    public List<Integer> findUserByDate(String date) {
        return logMapper.findUserByDate(date);
    }

}
