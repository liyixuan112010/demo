package com.xuan.realm;

import com.xuan.config.MyByteSource;
import com.xuan.pojo.User;
import com.xuan.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    
    @Resource
    private IUserService userService;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权...");
        String account = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        if(account.equals("admin")){
            roles.add("admin");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return  info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String account = (String) authenticationToken.getPrincipal();
        User user = userService.getUserByAccount(account);
        if(user==null){
            return null;
        }
        String pwd = new SimpleHash("MD5", user.getPassword(),account,1024).toString();
        return new SimpleAuthenticationInfo(account, pwd, new MyByteSource(account.getBytes()), getName());
    }
}
