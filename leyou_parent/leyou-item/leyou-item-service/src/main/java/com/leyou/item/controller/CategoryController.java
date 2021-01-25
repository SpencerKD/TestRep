package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.servie.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhantf
 * @date 2021/1/9 10:37
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

//    @CrossOrigin(origins = "http://manage.leyou.com") 解决跨域问题
    @GetMapping(value = "/list", produces = "application/json; charset=utf-8")//避免返回XML格式
    public ResponseEntity<List<Category>> findListByPid(@RequestParam("pid") Long pid){
        System.out.println("==========进入item-service服务=========");
        if (pid.longValue() < 0){//bad request
            return ResponseEntity.badRequest().build();
        }
        List<Category> categoryList = categoryService.findListByPid(pid);
        if (categoryList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryList);
    }

}
