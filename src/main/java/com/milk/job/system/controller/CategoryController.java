package com.milk.job.system.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.common.log.Log;
import com.milk.job.common.log.OperType;
import com.milk.job.model.pojo.Category;
import com.milk.job.model.vo.CategoryVo;
import com.milk.job.system.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 22:41
 */
@Slf4j
@RestController
@Api(value = "职位分类",tags = "category")
@RequestMapping("/job/category")
public class CategoryController {


    @Resource
    private CategoryService categoryService;


    @GetMapping("/all")
    @ApiOperation(value = "查询所有工作分类")
    public R getAll(){

        List<Category> categoryList = categoryService.list();
        return R.success(categoryList);
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询工作分类")
    public R getCategoryById(@PathVariable("id") Integer id){

        Category category = categoryService.getById(id);
        
        return R.success(category);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询分类")
    public R getPage(@RequestBody CategoryVo categoryVo){

        Page<Category> page = new Page<Category>(categoryVo.getPage(),categoryVo.getPageSize());

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(categoryVo.getName()), Category::getName,categoryVo.getName());
        Page<Category> pageInfo = categoryService.page(page,queryWrapper);
        return R.success(pageInfo);

    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除单个工作分类")
    @Log(title = "删除工作分类",businessType = OperType.DELETE)
    public R delCategory(@PathVariable("id") Integer id){
        categoryService.delCategory(id);
        
        return R.success("删除成功！");
    }

    @Log(title = "删除工作分类",businessType = OperType.DELETE)
    @DeleteMapping("/bathdel")
    @ApiOperation(value = "批量删除工作分类")
    public R batchDelCategory(@RequestBody List<Category> ids){
       String msg =  categoryService.batchDelCategory(ids);
        return R.success(msg);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加分类")
    @Log(title = "添加分类",businessType = OperType.INSERT)
    public R addCategory(@RequestBody Category category){

        categoryService.save(category);

        return R.success("添加成功！");
    }


    @PutMapping("/update")
    @ApiOperation(value = "修改分类")
    @Log(title = "修改工作分类",businessType = OperType.UPDATE)
    public R updateCategory(@RequestBody Category category){
        if (category.getId() == null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        categoryService.updateById(category);

        return R.success("修改成功!");
    }

}
