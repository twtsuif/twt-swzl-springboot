package com.suif.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suif.param.PostParam;
import com.suif.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suif.utils.Result;
import com.suif.vo.PostDetailVO;
import com.suif.vo.PostVO;

import java.util.List;

public interface PostService extends IService<Post> {

    List<PostVO> getPosts(Integer campus, Integer categoryId, String date, Page<Post> page);

    List<PostVO> listPostsByUserId(Long userId);

    List<PostVO> getEarlierPosts(Page<Post> page);

    PostDetailVO getPostDetail(Integer id);

    void publish(Post post);

    List<PostVO> postsToPostVOs(List<Post> posts);
}
