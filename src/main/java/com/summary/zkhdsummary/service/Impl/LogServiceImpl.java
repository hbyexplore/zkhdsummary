package com.summary.zkhdsummary.service.Impl;

import com.github.pagehelper.PageHelper;
import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.LogExample;
import com.summary.zkhdsummary.config.PageBean;
import com.summary.zkhdsummary.mapper.LogMapper;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
   private LogMapper logMapper;

    /***
     * 查找登录用户所有总记录，并进行分页展示
     * @param id
     * @return
     */
    @Override
    public PageBean<Log> findLogById(Integer id,int currement,int pageSize) {
        PageHelper.startPage(currement,pageSize);
        //查询全部内容
        List<Log> logList = logMapper.findLogById(id);
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
