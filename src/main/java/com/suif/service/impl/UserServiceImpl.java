package com.suif.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suif.param.UserUpdateParam;
import com.suif.entity.Category;
import com.suif.entity.Post;
import com.suif.entity.User;
import com.suif.ex.UserException;
import com.suif.mapper.CategoryMapper;
import com.suif.mapper.UserMapper;
import com.suif.service.PostService;
import com.suif.service.UserService;
import com.suif.utils.JwtUtil;
import com.suif.utils.Result;
import com.suif.vo.PostVO;
import com.suif.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sxd
 * @since 2022-02-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    PostService postService;

    @Resource
    JwtUtil jwtUtil;

    @Override
    public UserVO login(String username, String password) {
        User user = getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));

        if (user == null){
            throw new UserException("用户不存在");
        }
        if (!user.getPassword().equals(SecureUtil.md5(password))){
            throw new UserException("密码不正确");
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);

        String token = jwtUtil.createToken(user.getId());
        userVO.setToken(token);

        return userVO;
    }

    @Override
    public UserVO register(String username, String password) {
        if (getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username)) != null){
            throw new UserException("用户名已存在哦 请重新选择用户名");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(SecureUtil.md5(password));
        user.setAvatar("https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png");
        save(user);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);

        String token = jwtUtil.createToken(user.getId());
        userVO.setToken(token);

        return userVO;
    }

    @Override
    public List<PostVO> getPostByUser(Long id) {
        List<Post> posts = userMapper.getPostByUser();
        return postService.postsToPostVOs(posts);
    }

    @Override
    public void deletePost(Integer id) {
        userMapper.deletePost(id);
    }

    @Override
    public UserVO getUserById(Long id) {
        User user = getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }
}
