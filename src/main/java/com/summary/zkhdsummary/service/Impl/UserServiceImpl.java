package com.summary.zkhdsummary.service.Impl;

import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.bean.UserExample;
import com.summary.zkhdsummary.mapper.UserMapper;
import com.summary.zkhdsummary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

}
