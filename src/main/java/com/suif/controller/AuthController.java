package com.suif.controller;

import com.suif.param.LoginParam;
import com.suif.service.UserService;
import com.suif.shiro.ShiroUser;
import com.suif.utils.Result;
import com.suif.utils.ShiroUtil;
import com.suif.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Api(tags = "认证接口")
@RestController()
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    UserService userService;

    @Resource
    ShiroUtil shiroUtil;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginParam param) {
        UserVO userVO = userService.login(param.getUsername(),param.getPassword());
        return Result.success(userVO);
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody LoginParam param){
        UserVO userVO = userService.register(param.getUsername(),param.getPassword());
        return Result.success(userVO);
    }

    @PostMapping("/current")
    public Result getUserInfo(){
        ShiroUser current = shiroUtil.getCurrent();
        return Result.success(current);
    }
}
