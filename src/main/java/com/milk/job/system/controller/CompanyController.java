package com.milk.job.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.model.dto.CompanyDto;
import com.milk.job.model.pojo.Company;
import com.milk.job.model.pojo.Position;
import com.milk.job.model.vo.CompanyVo;
import com.milk.job.system.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 14:40
 */
@Api(value = "公司接口" ,tags = "company")
@RestController
@RequestMapping("/job/company")
@Slf4j
public class CompanyController {

    @Resource
    private CompanyService companyService;


    @GetMapping("/all")
    @ApiOperation(value = "查询所有公司")
    public R getAll(){
        List<Company> companyList = companyService.companyList();
        return R.success(companyList);
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询公司")
    public R pageSel(@RequestBody CompanyVo companyVo){
        Page<CompanyDto> page = companyService.pageSel(companyVo);
        return R.success(page);
    }

    @PostMapping("/{id}/{state}")
    @ApiOperation(value = "修改公司状态（包括审核）")
    public R changeState(@PathVariable("id") Integer id, @PathVariable("state") Integer state){
        companyService.changeState(id,state);

        return R.success("状态已修改！");

    }

    @PostMapping("/batchState")
    @ApiOperation(value = "批量审核")
    public R batchChangeState(@RequestBody List<Integer> ids){
        companyService.batchChangeState(ids);

        return R.success("操作成功！");

    }

    @PostMapping("/add")
    @ApiOperation(value = "添加公司")
    public R addCompany(@RequestBody Company company){

        log.info("数据：{}",company);
       companyService.addCompany(company);
        Integer id = company.getId();
        return R.success("添加成功！",id);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "根据id删除公司")
    public R delById(@PathVariable("id") Integer id){
        log.info("id:{}",id);
        companyService.delCompany(id);
        return R.success("删除成功！");
    }

    @DeleteMapping("/batchDel")
    @ApiOperation(value = "根据id批量删除公司")
    public R batchDelCompany(@RequestBody List<Integer> ids){
        companyService.batchDelCompany(ids);
        log.info("id:{}",ids);
        return R.success("删除成功！");
    }


    @PutMapping("/update")
    @ApiOperation(value = "根据id修改公司信息")
    public R updateCompany(@RequestBody Company company){

        companyService.updateById(company);

        return R.success("更新成功！");
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "根据id查询公司")
    public R getOne(@PathVariable("id") Integer id){
        Company company = companyService.getById(id);
        return R.success(company);
    }

    @PostMapping("/hotCom")
    @ApiOperation(value = "获取热门公司")
    public R getHotCompany(){
        List<Company> companyList = companyService.getHotCompany();
        return R.success(companyList);
    }
}
