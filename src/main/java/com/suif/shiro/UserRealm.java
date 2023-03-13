package com.suif.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.suif.entity.User;
import com.suif.service.UserService;
import com.suif.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class UserRealm extends AuthorizingRealm {

    @Resource
    JwtUtil jwtUtil;

    @Resource
    UserService userService;

    // 支持的token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    // 获取权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    // 传入token获得认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 根据token查用户id  反查数据库
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = (String) jwtToken.getPrincipal();

        // 校验jwt是否过期
        Claims claim = jwtUtil.parseJWT(token);
        if (claim==null|| jwtUtil.isExpired(claim.getExpiration())){
            throw new ExpiredCredentialsException("token已失效，请重新登录");
        }

        String userId = claim.getSubject();
        User user = userService.getById(userId);

        if (user==null){
            throw new UnknownAccountException("账户不存在");
        }

        // 赋给shiro的用户对象
        ShiroUser shiroUser = new ShiroUser();
        BeanUtil.copyProperties(user,shiroUser);

        return new SimpleAuthenticationInfo(shiroUser,jwtToken.getCredentials(),getName());
    }
}
