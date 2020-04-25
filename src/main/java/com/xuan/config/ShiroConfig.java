package com.xuan.config;

import com.xuan.filter.ShiroFilter;
import com.xuan.realm.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ShiroConfig {

    @Bean
    public CredentialsMatcher matcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(1024);
        matcher.setHashAlgorithmName("MD5");
        return matcher;
    }

    @Bean
    public MyRealm myRealm(CredentialsMatcher matcher){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(matcher);
        //开启缓存
        /*myRealm.setCachingEnabled(true);
        myRealm.setAuthenticationCachingEnabled(true);
        myRealm.setAuthenticationCacheName("aaa");
        myRealm.setAuthorizationCachingEnabled(true);
        myRealm.setAuthorizationCacheName("bbb");*/
        return myRealm;
    }
    @Bean
    public SecurityManager securityManager(MyRealm myRealm){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        /*defaultSecurityManager.setCacheManager(manager);*/
        defaultSecurityManager.setRealm(myRealm);
        return defaultSecurityManager;
    }


    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/index.html");
        bean.setSuccessUrl("/success.html");
        bean.setUnauthorizedUrl("/refuse.html");
        Map<String, Filter> filters = new HashMap<>();
        filters.put("rolesor",new ShiroFilter());
        bean.setFilters(filters);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/index.html", "anon");
        map.put("/user/login", "anon");
        map.put("/admin.html", "authc,roles[admin]");
        map.put("/all.html", "authc,roles[student]");
        map.put("/teacher.html", "authc,roles[teacher,admin]");
        map.put("/student.html", "authc,rolesor[admin,student,student]");
        map.put("/logout", "logout");
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

}
