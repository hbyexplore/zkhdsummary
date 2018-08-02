package com.summary.zkhdsummary.service;

import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.config.PageBean;

import java.util.List;

public interface LogService {
    //查询全部的总结内容
    PageBean<Log> findLogById(Integer id,int currement,int pageSize);

    List<Integer> findUserByDate(String date);


}
