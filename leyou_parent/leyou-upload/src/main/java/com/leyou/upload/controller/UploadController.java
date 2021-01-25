package com.leyou.upload.controller;

import com.leyou.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhantf
 * @date 2021/1/18 0:06
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    UploadService uploadService;
    /**
     * 请求方式：上传POST
     * 请求路径：/upload/image
     * 请求参数：文件，参数名是file，SpringMVC会封装为一个接口：MultipartFile
     * 返回结果：上传成功后得到的文件的url路径，也就是返回String
     */
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile multipartFile){
        String fileUrl = uploadService.upload(multipartFile);
        if(!StringUtils.isEmpty(fileUrl))
            return ResponseEntity.status(HttpStatus.CREATED).body(fileUrl);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
