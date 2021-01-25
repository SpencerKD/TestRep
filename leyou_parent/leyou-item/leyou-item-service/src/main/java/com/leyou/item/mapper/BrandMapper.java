package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhantf
 * @date 2021/1/10 20:24
 */
public interface BrandMapper extends Mapper<Brand> {
    @Insert("INSERT INTO `tb_category_brand` (tb_category_brand.`brand_id`, tb_category_brand.`category_id`) VALUES (#{bid}, #{cid})")
    int addBrandCategory(@Param("bid") Long bid, @Param("cid") Long cid);
}
