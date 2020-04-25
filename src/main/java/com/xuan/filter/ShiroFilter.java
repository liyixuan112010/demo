package com.xuan.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ShiroFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = super.getSubject(request,response);
        String[] roles = (String[]) mappedValue;
        if(roles!=null&&roles.length!=0){
            for (String role: roles ) {
                if(subject.hasRole(role)){
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
