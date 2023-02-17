package com.milk.job.system.controller;

import com.milk.job.common.R;
import com.milk.job.model.dto.HrCompanyDto;
import com.milk.job.model.pojo.HrCompany;
import com.milk.job.system.service.HrCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-29 17:51
 */

@Api(value = "HR所属公司接口" ,tags = "HRCompany")
@RestController
@RequestMapping("/job/hrCompany")
public class HrCompanyController {
    @Resource
    private HrCompanyService hrCompanyService;

    @GetMapping("/all/{id}")
    @ApiOperation(value = "根据公司id获取所有hr")
    public R getAll(@PathVariable("id") Integer id){

        List<HrCompanyDto> list= hrCompanyService.getAll(id);

        return R.success(list);


    }
}
