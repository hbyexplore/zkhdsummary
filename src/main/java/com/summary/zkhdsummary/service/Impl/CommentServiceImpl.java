package com.summary.zkhdsummary.service.Impl;

import com.summary.zkhdsummary.bean.Comment;
import com.summary.zkhdsummary.bean.CommentExample;
import com.summary.zkhdsummary.mapper.CommentMapper;
import com.summary.zkhdsummary.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

}
