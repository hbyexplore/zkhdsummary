package com.summary.zkhdsummary.service;

import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.LogBean;
import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.config.PageBean;

import java.util.List;

public interface LogService {
    Log findLogById(int id);
    //查询全部的总结内容
    PageBean<Log> findLogById(Integer id,int currement,int pageSize);

    List<Integer> findUserByDate(String date);

    PageBean<LogBean> searchLog(String username, String userdate, int currement, int pageSize);

    void addSummary(String username, String logTitle, String logContent);

    List<Log> findAllByHot();

    List<LogBean> findAllByCommentTotal();
}
