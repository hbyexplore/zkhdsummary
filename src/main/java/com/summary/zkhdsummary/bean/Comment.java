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
public class Comment implements Serializable {
    private Integer id;

    private Date date;

    private Integer logId;

    private Integer userId;

    private String comment;

}