package com.summary.zkhdsummary.service;

import com.summary.zkhdsummary.bean.Comment;
import com.summary.zkhdsummary.bean.Detail;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CommentService {

    List<Detail> findDetailById(int id);

    int insterComment(Integer log_id,String userName, String content);
}
