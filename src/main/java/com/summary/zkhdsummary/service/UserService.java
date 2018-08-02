package com.summary.zkhdsummary.service;

import com.summary.zkhdsummary.bean.User;

import java.util.List;

public interface UserService {
   public List<User> findAllUser();
   List<Integer> findAllCrad();

   String findUserByCrad(int crad);

   Integer insterUser(User user);
}
