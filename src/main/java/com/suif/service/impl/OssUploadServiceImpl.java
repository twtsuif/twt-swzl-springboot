package com.suif.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.suif.service.UploadService;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Data
@ConfigurationProperties(prefix = "suif.oss")
public class OssUploadServiceImpl implements UploadService {

    private String endpoint;

    private String bucket;

    private String accessKeyId;

    private String accessKeySecret;

    public String uploadFile(MultipartFile multipartFile){
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //桶不存在就重新创建 并且访问权限为公共读
            if (!ossClient.doesBucketExist(bucket)){
                ossClient.createBucket(bucket);
                ossClient.setBucketAcl(bucket, CannedAccessControlList.PublicRead);
            }

            // 获取文件上传的流 构建日期目录
            InputStream inputStream = multipartFile.getInputStream();
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dataPath = dataFormat.format(new Date());

            //获取文件名
            String originalFilename = multipartFile.getOriginalFilename();
            String filename = UUID.randomUUID().toString();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newName = filename+suffix;
            String fileUrl = dataPath+"/"+newName;

            //文件上传
            ossClient.putObject(bucket, fileUrl, inputStream);

            return "https://"+bucket+"."+endpoint+"/"+fileUrl;
        } catch (Exception e){
            e.printStackTrace();
            return "fail";
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }
}
