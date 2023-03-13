package com.suif.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.suif.entity.Tags;
import com.suif.service.TagsService;
import com.suif.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags ="标签接口")
@RestController
@RequestMapping("/api/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @ApiOperation(value="根据一级种类id查询标签")
    @GetMapping("{categoryId}")
    public Result list(@PathVariable Integer categoryId){
        LambdaQueryWrapper<Tags> lqw = new LambdaQueryWrapper();
        lqw.eq(Tags::getCategoryId,categoryId);
        return Result.success(tagsService.list(lqw));
    }



}

