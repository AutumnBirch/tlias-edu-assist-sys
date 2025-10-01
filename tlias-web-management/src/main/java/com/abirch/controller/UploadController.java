package com.abirch.controller;

import com.abirch.pojo.Result;
import com.abirch.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 本地磁盘存储的方案
     */
    /*@PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收参数：{},{},{}",name,age,file);
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        String newFileName = UUID.randomUUID()+ extension;
        // 保存文件
        file.transferTo(new File("C:\\04_Download\\"+newFileName));
        return Result.success();
    }*/

    /**
     *
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());
        // 将文件交给阿里云OSS存储
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS，URL：{}",url);
        return Result.success(url);
    }

}
