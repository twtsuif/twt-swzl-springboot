package com.suif.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suif.param.PostParam;
import com.suif.entity.Post;
import com.suif.ex.InValidAccessException;
import com.suif.param.PostSelectParam;
import com.suif.service.PostService;
import com.suif.utils.Result;
import com.suif.utils.ShiroUtil;
import com.suif.vo.PostDetailVO;
import com.suif.vo.PostVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@Api(tags = "帖子接口")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Resource
    PostService postService;

    @Resource
    ShiroUtil shiroUtil;

    @ApiOperation("获取招领帖")
    @GetMapping
    public Result get(PostSelectParam param) {
        Page<Post> page = param.getPage();
        List<PostVO> list = postService.getPosts(param.getCampus(), param.getCategoryId(), param.getDate(), page);
        return Result.success(list);
    }

    @ApiOperation("获取当前用户的招领帖")
    @GetMapping("user")
    public Result getCurrentPosts() {
        Long currentId = shiroUtil.getCurrentId();
        List<PostVO> list = postService.listPostsByUserId(currentId);
        return Result.success(list);
    }

    @ApiOperation("获取更早的招领帖 7天前")
    @GetMapping("earlier")
    public Result getEarlierPosts(Integer pageCurrent, Integer pageSize){
        Page<Post> page = new Page<>(pageCurrent,pageSize);
        List<PostVO> list = postService.getEarlierPosts(page);
        return Result.success(list);
    }

    @ApiOperation("获取招领的详细信息")
    @GetMapping("detail/{id}")
    public Result detail(@PathVariable("id") Integer id) {
        PostDetailVO postDetailVO = postService.getPostDetail(id);
        return Result.success(postDetailVO);
    }

    @ApiOperation("发布招领帖")
    @PostMapping
    public Result publish(@Validated @RequestBody PostParam param) {
        Post post = new Post();
        BeanUtils.copyProperties(param,post);
        Long currentId = shiroUtil.getCurrentId();
        post.setUserId(currentId);

        postService.publish(post);
        return Result.success(null);
    }

    @ApiOperation("删除招领贴")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id) {
        Post post = postService.getById(id);
        Long currentId = shiroUtil.getCurrentId();
        if (!Objects.equals(post.getUserId(), currentId)) {
            throw new InValidAccessException("非法删除");
        }

        postService.remove(Wrappers.<Post>lambdaQuery().eq(Post::getId, id));
        return Result.success(null);
    }
}

