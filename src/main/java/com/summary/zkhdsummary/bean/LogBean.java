package com.summary.zkhdsummary.bean;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class LogBean implements Serializable {
/**
 *u.id,u.name,l.title,l.date,l.log,COUNT(c.id)
 * 接收返回的数据类用于展示首页信息
 * */
private Integer uid;

private Integer lid;

private String  username;

private String  title;

private Date    logDate;

private String  logContent;

private Integer commentCount;
}
