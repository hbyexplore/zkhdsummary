package com.summary.zkhdsummary.mapper;

import com.summary.zkhdsummary.bean.Log;

import java.util.List;

public interface LogMapper {
    List<Log> findLogById(Integer id);
    int countLog(Integer id);
    List<Integer> findUserByDate(String date);
}