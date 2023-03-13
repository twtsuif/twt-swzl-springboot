package com.suif.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suif.config.RabbitMQConfig;
import com.suif.dto.MQMessage;
import com.suif.entity.Category;
import com.suif.entity.Post;
import com.suif.mapper.PostMapper;
import com.suif.service.CategoryService;
import com.suif.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suif.vo.PostDetailVO;
import com.suif.vo.PostVO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Resource
    PostMapper postMapper;

    @Resource
    CategoryService categoryService;

    @Resource
    AmqpTemplate amqpTemplate;

    @Override
    public List<PostVO> getPosts(Integer campus, Integer categoryId, String date, Page<Post> page) {
        List<Post> posts = postMapper.getPosts(campus,categoryId,date,page);
        return postsToPostVOs(posts);
    }

    @Override
    public void publish(Post post) {
        save(post);
        MQMessage message = new MQMessage();
        BeanUtil.copyProperties(post, message);
        message.setId(post.getId());
        amqpTemplate.convertAndSend(RabbitMQConfig.FIND_EXCHANGE, RabbitMQConfig.ROUNTING_KEY, message);
    }

    @Override
    public List<PostVO> listPostsByUserId(Long userId) {
        List<Post> posts = list(Wrappers.<Post>lambdaQuery().eq(Post::getUserId, userId).orderByDesc(Post::getCreated));
        return postsToPostVOs(posts);
    }

    @Override
    public List<PostVO> getEarlierPosts(Page<Post> page) {
        IPage<Post> postIPage = postMapper.selectEarlier(page);
        List<Post> posts = postIPage.getRecords();
        return postsToPostVOs(posts);
    }

    @Override
    public PostDetailVO getPostDetail(Integer id) {
        PostDetailVO postDetailVO = new PostDetailVO();
        Post post = getById(id);
        BeanUtils.copyProperties(post,postDetailVO);
        Category categoryName = categoryService.getById(post.getCategoryId());
        postDetailVO.setCategoryName(categoryName.getCategoryName());
        return postDetailVO;
    }

    private PostVO postToPostVO(Post post){
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post,postVO);
        Category category = categoryService.getById(post.getCategoryId());
        postVO.setCategoryName(category.getCategoryName());
        return postVO;
    }

    @Override
    public List<PostVO> postsToPostVOs(List<Post> posts) {
        List<PostVO> list = new ArrayList<>();
        for (Post post : posts) {
            list.add(postToPostVO(post));
        }
        return list;
    }
}
