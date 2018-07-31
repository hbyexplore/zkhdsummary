package com.summary.zkhdsummary.bean;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

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


}