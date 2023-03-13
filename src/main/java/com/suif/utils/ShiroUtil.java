package com.suif.utils;


import com.suif.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

@Component
public class ShiroUtil {
    public ShiroUser getCurrent() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    public Long getCurrentId() {
        return getCurrent().getId();
    }
}
