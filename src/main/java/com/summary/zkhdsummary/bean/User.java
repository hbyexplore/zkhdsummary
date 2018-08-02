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

    private Power UserPower;

}