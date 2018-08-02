package com.summary.zkhdsummary.service.Impl;

import com.summary.zkhdsummary.bean.Comment;
import com.summary.zkhdsummary.bean.Detail;
import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.mapper.CommentMapper;
import com.summary.zkhdsummary.mapper.LogMapper;
import com.summary.zkhdsummary.mapper.UserMapper;
import com.summary.zkhdsummary.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过日志ID查询出 日志评论
     * @param id
     * @return
     */
    @Override
    public List<Detail> findDetailById(int id) {
        List<Detail> detailById = commentMapper.findDetailById(id);

        logMapper.update2Hot(id);
        return detailById;
    }

    /**
     * 保存评论
     * @param log_id
     * @param userName
     * @param contentt
     * @return
     */
    @Override
    public int insterComment(Integer log_id,String userName, String contentt) {

        //根据姓名查到id
        User userIdByName = userMapper.findUserIdByName(userName);

        //保存对象
        Comment comment = new Comment();

        Log log = new Log();
        log.setId(log_id);

        comment.setComment(contentt);
        comment.setDate(new Date());
        comment.setLog(log);
        comment.setUser(userIdByName);


        int i = commentMapper.insertComment(comment);
        return i;
    }
}
