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
public class Power implements Serializable {
    private Integer id;
    private String power;

}