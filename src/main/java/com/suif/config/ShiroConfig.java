package com.suif.config;

import com.suif.shiro.UserRealm;
import com.suif.shiro.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置自定义的Realm
        securityManager.setRealm(userRealm);

        // 关闭Shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    // 配置过滤器组
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();

        // 配置securityManager
        shiroFilter.setSecurityManager(securityManager);

        // 配置自定义的过滤器
        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt", new JwtFilter());   // 重要bug 不可以使用@Resource
        shiroFilter.setFilters(filters);

        // 配置请求路径
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/api/auth/login","anon");
        filterMap.put("/api/auth/register","anon");
        filterMap.put("/hello","anon");
        filterMap.put("/**", "jwt");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }
}
