package com.summary.zkhdsummary.service.Impl;

import com.summary.zkhdsummary.bean.Comment;
import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.mapper.CommentMapper;
import com.summary.zkhdsummary.mapper.LogMapper;
import com.summary.zkhdsummary.mapper.UserMapper;
import com.summary.zkhdsummary.service.LogService;
import com.summary.zkhdsummary.utils.FormatDuring;
import com.summary.zkhdsummary.utils.RemoveDuplicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<User> findList() {
        //查询所有的log
        List<Log> list = logMapper.findList();
        //根据每个log在查出每个user用户
        List<User> users= new ArrayList<>();
        for (Log log : list) {
            //获取log对应的userid
            Integer userid = log.getUserid();
            //根据userid查出对应的user信息
            User user=userMapper.findByid(userid);
            //添加到集合中
            users.add(user);
        }
        //去重
        List<User> userList = RemoveDuplicate.removeDuplicate(users);
        return userList;
    }
    @Override
    public List findTime(List<User> list) {
        //根据userid查询出其发表总结的时间
        //将每个用户对应的所有总结时间都存入
        for (User user : list) {
            Integer id = user.getId();
            List<Date>time=logMapper.findTime(id);
            //将每个用户的所有总结的时间都存入相应的用户
            user.setSummaryTime(time);
        }
        //获取当前毫秒值
        long nowTime = System.currentTimeMillis();
        //将每个用户中的总结时间与当前毫秒值比较找出最新的一个总结
        //最大毫秒值标记
        long maxTime=0L;
        for (User user : list) {
            List<Date> summaryTime = user.getSummaryTime();
            //如果每个用户只有一条总结直接获取总结的毫秒值
            if (summaryTime.size()==1){
                maxTime = summaryTime.get(0).getTime();
            }else if (summaryTime.size()>1){
                //如果每个用户的总结大于1条就比较出最晚写的总结的毫秒值
                for (int i=1;i<summaryTime.size();i++){
                    if (summaryTime.get(i-1).getTime() < summaryTime.get(i).getTime()){
                        maxTime=summaryTime.get(i).getTime();
                    }
                }
            }
            //毫秒值进化到分钟
            long l = nowTime - maxTime;
            String s = FormatDuring.formatDuring(l);
            user.setNewTime(s);
        }
        return list;
    }

    @Override
    public List<List<Log>> findSummary(List<User> listTime) {
        HashMap<Integer, List<Log>> objectObjectHashMap = new HashMap<>();
        for (User user : listTime) {
            //根据user id 获取所有总结
            List<Log> summaryById1 = logMapper.findSummaryById(user.getId());
            //存入map集合去重
            objectObjectHashMap.put(user.getId(),summaryById1);
        }
        //遍历
        Set<Map.Entry<Integer, List<Log>>> entries = objectObjectHashMap.entrySet();
        List<List<Log>>logs=new ArrayList<>();
        for (Map.Entry<Integer, List<Log>> entry : entries) {
            List<Log> value = entry.getValue();
            for (Log log : value) {
                //根据 log id 获取每个总结对应的评论
                List<Comment> logConut = commentMapper.findLogConut(log.getId());
                //将每条总结对应的所有评论都放入其中
                log.setComment(logConut);
            }
            logs.add(value);
        }
        return logs;
    }
}
