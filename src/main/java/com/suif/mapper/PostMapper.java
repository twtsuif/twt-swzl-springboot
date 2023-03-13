package com.suif.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suif.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sxd
 * @since 2022-01-04
 */

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    List<Post> getPosts(Integer campus, Integer categoryId, String date, Page<Post> page);

    IPage<Post> selectEarlier(Page<Post> page);
}
