package com.summary.zkhdsummary.mapper;

import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.LogExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {
       List<Log> findList();
       List<Date> findTime(Integer id);
       List<Log> findSummaryById(Integer id);
}