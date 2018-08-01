package com.summary.zkhdsummary.bean;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class User implements Serializable {
    private Integer id;

    private String name;

    private String department;

    private String password;

    private Integer card;

    private Power userPower; //权限默认为user

    //存储当前用户的所有的总结时间
    private List<Date> summaryTime;

    //存储当前用户最新的总结时间
    private String newTime;

    //存储当前用户最新总结的评论条数
    private Integer commentCount;



}