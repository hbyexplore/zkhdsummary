package com.summary.zkhdsummary.mapper;

import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.LogBean;
import com.summary.zkhdsummary.bean.LogExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {
       Log findLogById(Integer id);

       LogBean findList(Integer id);

       Integer findListById(Integer id);

       List<Integer> findSummaryById();

       List<LogBean> searchLog(@Param("username") String username,@Param("userdate") String userdate);

       void insertSummary(HashMap<String,Object> summaryMap);

       List<Log>findAllLogById(Integer id);

       int countLog(Integer id);

       List<Integer> findUserByDate(String date);

       void update2Hot(Integer id);

       List<Log> findAllByHot();

       List<Log> finAll();
}