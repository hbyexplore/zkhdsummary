package com.summary.zkhdsummary.service;

import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.User;

import java.util.List;

public interface LogService {
    List<User> findList();

    List findTime(List<User> list);

    List<List<Log>> findSummary(List<User> listTime);
}
