package com.suif.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.suif.param.UserUpdateParam;
import com.suif.entity.User;
import com.suif.ex.UserException;
import com.suif.ex.InValidAccessException;
import com.suif.service.UserService;
import com.suif.shiro.ShiroUser;
import com.suif.utils.Result;
import com.suif.utils.ShiroUtil;
import com.suif.vo.PostVO;
import com.suif.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    ShiroUtil shiroUtil;

    @ApiOperation("获取用户的信息")
    @GetMapping("/info/{id}")
    public Result getUserById(@PathVariable Long id) {
        UserVO userVO = userService.getUserById(id);
        return Result.success(userVO);
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/update")
    public Result update(@RequestBody UserUpdateParam param) {
        ShiroUser current = shiroUtil.getCurrent();
        // 用户修改了用户名 并且用户名已存在
        if (!Objects.equals(current.getUsername(), param.getUsername()) &&
                userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, param.getUsername())) != null) {
            return Result.fail("用户名已存在");
        }

        Long currentId = current.getId();
        User update = new User();
        BeanUtils.copyProperties(param, update);
        update.setId(currentId);

        userService.updateById(update);

        return Result.success(null);
    }

    @ApiOperation("获取用户匹配到的招领贴")
    @GetMapping("ask")
    public Result getUserPost() {
        Long currentId = shiroUtil.getCurrentId();
        List<PostVO> list = userService.getPostByUser(currentId);
        return Result.success(list);
    }

    @ApiOperation("删除匹配到的招领帖")
    @DeleteMapping("{id}")
    public Result deleteUserPost(@PathVariable Integer id) {
        userService.deletePost(id);
        return Result.success(null);
    }
}
