package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.servie.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhantf
 * @date 2021/1/10 20:28
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping(value = "/page", produces = "application/json; charset=utf-8")
    // Param: key=&page=1&rows=5&sortBy=id&desc=false
    public ResponseEntity<PageResult<Brand>> findBrandsByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc){
        PageResult<Brand> brandsByPage = this.brandService.findBrandsByPage(key, page, rows, sortBy, desc);
        if(brandsByPage == null || CollectionUtils.isEmpty(brandsByPage.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brandsByPage);
    }


    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        brandService.addBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
