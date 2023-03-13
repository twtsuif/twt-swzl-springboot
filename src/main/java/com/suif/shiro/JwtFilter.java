package com.suif.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {

    // 没有token拒绝访问
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        // 没有token的话直接拒绝
        if (ObjectUtils.isEmpty(jwt)){
            return true;
        }
        return executeLogin(servletRequest,servletResponse);
    }

    // 放入Token 下一步AccountRealm使用
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        return new JwtToken(jwt);
    }
}
