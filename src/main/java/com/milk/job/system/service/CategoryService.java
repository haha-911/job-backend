package com.milk.job.system.service;

import java.util.List;
import java.util.Map;

import com.milk.job.model.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
    
/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface CategoryService extends IService<Category>{


    int batchInsert(List<Category> list);

    void delCategory(Integer id);

    String batchDelCategory(List<Category> ids);
}
