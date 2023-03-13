package com.suif.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suif.param.AskParam;
import com.suif.entity.Ask;
import com.suif.ex.InValidAccessException;
import com.suif.service.AskService;
import com.suif.shiro.ShiroUser;
import com.suif.utils.Result;
import com.suif.utils.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Api(tags = "求助接口")
@RestController
@RequestMapping("/api/asks")
public class AskController {

    @Resource
    ShiroUtil shiroUtil;

    @Resource
    private AskService askService;

    @ApiOperation("查看自己所有的寻物贴")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page<Ask> page = new Page<>(currentPage, 5);
        Long currentId = shiroUtil.getCurrentId();
        List<Ask> asks = askService.listAsks(page, currentId);
        return Result.success(asks);
    }

    @ApiOperation("新增寻物贴")
    @PostMapping
    public Result save(@Validated @RequestBody AskParam param){
        askService.add(param);
        return Result.success(null);
    }

    @ApiOperation("删除寻物贴")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        Ask ask = askService.getById(id);
        Long currentId = shiroUtil.getCurrentId();
        if (!Objects.equals(currentId, ask.getUserId())){
            throw new InValidAccessException("非法删除哦~");
        }
        askService.deleteAsk(id);
        return Result.success(null);
    }
}
