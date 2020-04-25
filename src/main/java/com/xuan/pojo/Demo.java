package com.xuan.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo implements Serializable {
    private Integer id;
    private Integer uid;
    private String name;
}
