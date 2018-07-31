package com.summary.zkhdsummary.service.Impl;

import com.summary.zkhdsummary.bean.Power;
import com.summary.zkhdsummary.bean.PowerExample;
import com.summary.zkhdsummary.mapper.PowerMapper;
import com.summary.zkhdsummary.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerMapper powerMapper;
}
