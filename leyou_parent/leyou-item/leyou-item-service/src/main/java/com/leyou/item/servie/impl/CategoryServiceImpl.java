package com.leyou.item.servie.impl;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.servie.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhantf
 * @date 2021/1/9 9:46
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    private  Example createExample(Map<String, Object> searchMap){
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap != null){
            //按主键category id查询
            if(searchMap.get("id") != null){
                Long id = (Long) searchMap.get("id");
                criteria.andEqualTo("id", id);
            }
            //按名称category name查询
            if(searchMap.get("name") != null && !"".equals(searchMap.get("name"))){
                String name = (String) searchMap.get("name");
                criteria.andLike("id", "%" + name +"%");
            }
            //按上级id category parentId查询
            if(searchMap.get("parentId") != null){
                Long parentId = (Long) searchMap.get("parentId");
                criteria.andEqualTo("parentId", parentId);
            }
            //按是否是父级id category isParent查询
            if(searchMap.get("isParent") != null){
                Boolean isParent = (Boolean) searchMap.get("isParent");
                criteria.andEqualTo("isParent", isParent);
            }

            //按排序优先级sort category sort查询
            if(searchMap.get("sort") != null && !"".equals(searchMap.get("sort"))){
                Object sort = searchMap.get("sort");
                criteria.andEqualTo("sort", sort);
            }
        }
        return example;
    }

    @Override
    public List<Category> findListByPid(Long pid) {
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("parentId", pid);
        Example example = createExample(searchMap);
        List<Category> categoryList = categoryMapper.selectByExample(example);
//        Category category = new Category();
//        category.setParentId(pid);
//        List<Category> categoryList = categoryMapper.select(category);
        return categoryList;

    }
}
