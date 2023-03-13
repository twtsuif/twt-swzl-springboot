package com.suif.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suif.entity.Post;
import com.suif.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sxd
 * @since 2022-02-10
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Post> getPostByUser();

    void deletePost(Integer id);
}
