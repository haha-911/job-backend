package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.pojo.Position;
import com.milk.job.system.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;

import com.milk.job.system.mapper.CategoryMapper;
import com.milk.job.model.pojo.Category;
import com.milk.job.system.service.CategoryService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    @Autowired
    private PositionMapper positionMapper;



    @Override
    public int batchInsert(List<Category> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public void delCategory(Integer id) {
        if (id == null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        int count = positionMapper.selectCountByCategoryId(id);

        if (count>0){
            throw new CustomerException("种类下有正在招聘的职位，暂不能删除！",500);
        }

        this.removeById(id);
    }

    @Override
    public String batchDelCategory(List<Category> categories) {
        String noMsg = "";
        for (Category category : categories) {
            Integer count = positionMapper.selectCountByCategoryId(category.getId());
            if (count>0){
                noMsg=noMsg + category.getName()+"、";
            }
            if (count ==0){
                this.removeById(category.getId());
            }

        }
        return noMsg;
    }
}
