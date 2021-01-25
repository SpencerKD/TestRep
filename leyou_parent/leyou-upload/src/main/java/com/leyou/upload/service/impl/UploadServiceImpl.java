package com.leyou.upload.service.impl;

import com.leyou.upload.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhantf
 * @date 2021/1/18 0:20
 */
@Service
public class UploadServiceImpl implements UploadService {

    private static final String[] CONTENT_TYPES = {"image/gif", "image/jpeg"};
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);
    @Override
    public String upload(MultipartFile multipartFile) {
        /*
        校验文件大小
        校验文件的媒体类型
        校验文件的内容
        保存内容
        返回URL
         */
        String originalFilename = multipartFile.getOriginalFilename();
        String content_type = multipartFile.getContentType();
        //或者直接用Stringutils切割
        if(Arrays.asList(CONTENT_TYPES).contains(content_type)){
            try {
                BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
                if(bufferedImage == null){
                    LOGGER.info("file content error:{ }", originalFilename);
                    return null;
                }
                multipartFile.transferTo(new File("C:\\leyou\\images\\" + originalFilename));
                return "http://image.leyou.com/" + originalFilename;//image url
                /**
                 * 为什么图片地址需要使用另外的url？
                 * 图片不能保存在服务器内部，这样会对服务器产生额外的加载负担
                 * 一般静态资源都应该使用独立域名，这样访问静态资源时不会携带一些不必要的cookie，减小请求的数据量
                 */
            }catch (Exception e){
                LOGGER.info("服务器内部错误...");
            }
        }else {
            LOGGER.info("file type error: { }", originalFilename);
            return null;
        }
        return null;
    }
}
