package com.summary.zkhdsummary.mapper;

import com.summary.zkhdsummary.bean.Comment;
import com.summary.zkhdsummary.bean.CommentExample;
import java.util.List;

import com.summary.zkhdsummary.bean.Detail;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {

    List<Comment> findLogConut(Integer id);

    List<Detail> findDetailById(Integer id);

    int insertComment(Comment comment);
}