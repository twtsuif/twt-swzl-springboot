package com.suif.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.suif.entity.Category;
import com.suif.service.CategoryService;
import com.suif.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags ="种类接口")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @ApiOperation(value="根据一级种类id查询所有二级种类")
    @GetMapping("{upId}")
    public Result list(@PathVariable("upId") Integer upId){
        List<Category> list = categoryService.list(Wrappers.<Category>lambdaQuery().eq(Category::getUpId, upId));
        return Result.success(list);
    }
}

