package com.leyou.item.servie;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhantf
 * @date 2021/1/10 20:25
 */
public interface BrandService {

    PageResult<Brand> findBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    void addBrand(Brand brand, List<Long> cid);
}
