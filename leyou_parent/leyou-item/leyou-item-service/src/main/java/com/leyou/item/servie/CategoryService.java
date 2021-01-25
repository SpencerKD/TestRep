package com.leyou.item.servie;

import com.leyou.item.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhantf
 * @date 2021/1/9 9:43
 */

public interface CategoryService {
     List<Category> findListByPid(Long pid);
}
