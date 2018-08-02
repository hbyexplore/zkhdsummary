package com.summary.zkhdsummary.service.Impl;


import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.mapper.LogMapper;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

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
}
