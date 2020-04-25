package com.xuan.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Integer flag;
    private Addr addr;
    private List<Demo> demos;
    private String account;
    private String password;
}
