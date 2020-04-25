package com.xuan.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Addr implements Serializable {
    private String name;
    private Integer id;
    private Integer uid;
}
