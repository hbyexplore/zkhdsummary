package com.summary.zkhdsummary.service;

import com.summary.zkhdsummary.bean.Log;
import org.springframework.ui.Model;
import com.summary.zkhdsummary.bean.LogBean;
import com.summary.zkhdsummary.bean.User;

import java.util.List;

public interface LogService {
    Log findLogById(int id);
    List<LogBean> findList();

    List findTime(List<User> list);

    List<List<Log>> findSummary(List<User> listTime);

    List<LogBean> searchLog(String username, String userdate);

    void addSummary(String username, String logTitle, String logContent);
}
