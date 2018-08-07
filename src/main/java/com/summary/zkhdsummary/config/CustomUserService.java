package com.summary.zkhdsummary.config;

import com.summary.zkhdsummary.bean.Power;
import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(s);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。
                authorities.add(new SimpleGrantedAuthority(user.getUserPower().getPower()));
            System.out.println(authorities);
//        user.getName(),user.getPassword(),authorities

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),authorities);
    }
}
