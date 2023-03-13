package com.suif.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suif.param.UserUpdateParam;
import com.suif.entity.User;
import com.suif.utils.Result;
import com.suif.vo.PostVO;
import com.suif.vo.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sxd
 * @since 2022-02-10
 */
public interface UserService extends IService<User> {

    UserVO login(String username, String password);

    UserVO register(String username, String password);

    // 获取用户 匹配到的招领贴
    List<PostVO> getPostByUser(Long id);

    // 删除用户 匹配到的招领贴
    void deletePost(Integer id);

    UserVO getUserById(Long id);
}
