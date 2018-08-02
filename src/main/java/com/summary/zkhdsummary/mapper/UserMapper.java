package com.summary.zkhdsummary.mapper;

import com.summary.zkhdsummary.bean.User;

import java.util.List;


public interface UserMapper {
    public List<User> findAllUser() ;

    int insertUser(User user);

    User findByid(Integer userid);
}