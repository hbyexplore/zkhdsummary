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
public class Log implements Serializable {
    private Integer id;

    private Date date;

    private Integer hot = 0; //热度默认为0

    private String title;

    private User user;

    private String log;

}