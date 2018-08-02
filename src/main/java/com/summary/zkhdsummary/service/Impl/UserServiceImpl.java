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

    /**
     * 查出所有的用户
     * @return
     */
    @Override
    public List<User> findAllUser() {
        List<User> users = userMapper.findAllUser();
        return users;
    }

    @Override
    public List<Integer> findAllCrad() {
        return userMapper.findAllCrad();
    }

    @Override
    public String findUserByCrad(int crad) {
        return userMapper.findUserByCrad(crad);
    }

    /**
     * 将用户保存在数据库
     * @param user
     * @return
     */
    @Override
    public Integer insterUser(User user) {

        int i = userMapper.insertUser(user);
        return i ;
    }

    /**
     *  通过username获取user id
     * @param username
     * @return
     */
    @Override
    public User findUserIdByName(String username) {
        return userMapper.findUserIdByName(username);
    }
}
