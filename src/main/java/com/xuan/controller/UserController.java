package com.xuan.controller;

import com.xuan.pojo.User;
import com.xuan.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/userAll")
    public String userAll(){
        String s = userService.userAll().toString();
        System.out.println(s);
        return s;
    }

    @RequestMapping("/login")
    public String login(User user){
        System.out.println("登录");
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(),user.getPassword());
            try {
                subject.login(token);
                return "/success.html";
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("认证失败");
            return "/index.html";
        }
        return "/success.html";
    }
}
