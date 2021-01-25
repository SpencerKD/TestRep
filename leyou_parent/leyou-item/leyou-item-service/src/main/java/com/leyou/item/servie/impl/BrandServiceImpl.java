package com.leyou.item.servie.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.servie.BrandService;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zhantf
 * @date 2021/1/10 20:27
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;

    /**
     * 封装查询条件example
     * @param key
     * @param sortBy
     * @param desc
     * @return
     */
    private Example createExample(String key, String sortBy, Boolean desc){
        //分页条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(!"".equals(key) || key == null){//根据key进行的name的模糊查询或者根据首字母查询
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        //添加排序条件
        if(!"".equals(key) || key == null){
            example.setOrderByClause(sortBy + (desc? " desc" : " asc"));//添加排序字段
        }

        return example;
    }

    @Override
    public PageResult<Brand> findBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {

        Example example = createExample(key, sortBy, desc);

        PageHelper.startPage(page, rows);

        List<Brand> brands = this.brandMapper.selectByExample(example);

        /*
        封装pageInfo
         */
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        /*
        分页结果集
         */
        PageResult<Brand> pageResult = new PageResult<Brand>(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return pageResult;
    }


    /*
    两个任务绑定，按照事务处理
     */
    @Transactional
    @Override
    public void addBrand(Brand brand, List<Long> cids) {
        //首先新增Brand
        brandMapper.insertSelective(brand);//更新成功，返回1

        //再将Brand的id和cid新增到中间表 brand和category是ER关系为多对多
        cids.forEach(cid ->{
            brandMapper.addBrandCategory(brand.getId(), cid);
        });

    }
}
