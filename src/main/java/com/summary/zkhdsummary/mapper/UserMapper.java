package com.summary.zkhdsummary.mapper;

import com.summary.zkhdsummary.bean.User;

import java.util.List;


public interface UserMapper {
    public List<User> findAllUser() ;

    int insertUser(User user);

    User findByid(Integer userid);

    List<Integer> findAllCrad();
    //根据crad查询出用户的名称
    String findUserByCrad(int crad);
}