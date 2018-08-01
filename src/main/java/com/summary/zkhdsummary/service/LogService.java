package com.summary.zkhdsummary.service;

import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.LogBean;
import com.summary.zkhdsummary.bean.User;

import java.util.List;

public interface LogService {
    List<LogBean> findList();

    List findTime(List<User> list);

    List<List<Log>> findSummary(List<User> listTime);

    List<LogBean> searchLog(String username, String userdate);
}
