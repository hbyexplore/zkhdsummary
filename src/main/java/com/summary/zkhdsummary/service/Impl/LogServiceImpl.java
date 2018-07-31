package com.summary.zkhdsummary.service.Impl;

import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.LogExample;
import com.summary.zkhdsummary.mapper.LogMapper;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
   private LogMapper logMapper;
}
