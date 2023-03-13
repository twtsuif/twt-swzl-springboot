package com.suif.controller;


import com.suif.service.UploadService;
import com.suif.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

@Api(tags ="图片接口")
@RestController
@RequestMapping("/api")
public class UploadController {

    @Resource
    UploadService ossUploadServiceImpl;

    @ApiOperation(value="图片上传")
    @PostMapping("/upload")
    private Result upload(@RequestParam("file") MultipartFile multipartFile){
        String url = ossUploadServiceImpl.uploadFile(multipartFile);
        return Result.success(url);
    }
}
