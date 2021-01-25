package com.leyou.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhantf
 * @date 2021/1/18 0:18
 */
public interface UploadService {
    /**
     *  校验文件大小
     *  校验文件的媒体类型
     *  校验文件的内容
     * @param multipartFile
     * @return
     */
     String upload(MultipartFile multipartFile) ;
}
