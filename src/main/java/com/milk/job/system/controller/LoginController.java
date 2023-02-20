package com.milk.job.system.controller;

import com.alibaba.druid.util.StringUtils;
import com.milk.job.common.R;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.common.utils.TokenUtils;
import com.milk.job.model.dto.UserDto;
import com.milk.job.model.pojo.*;
import com.milk.job.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2023-01-11 14:21
 */
@Slf4j
@RestController
@Api(value = "登录",tags = "login")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private HrCompanyService hrCompanyService;
    @Resource
    private ResumeService resumeService;

    @PostMapping("/login")
    @ApiOperation("后台管理员登录")
    public R login(@RequestBody User user){
        String token = userService.login(user);

        return R.success("登录成功！",token);
    }

    @PostMapping("/registry")
    @ApiOperation("用户注册")
    public R registry(@RequestBody User user){

        userService.registry(user);

        return R.success("注册成功！");
    }

    @PostMapping("/user/login")
    @ApiOperation("用户登录")
    public R userLogin(@RequestParam("name") String username ,@RequestParam("pwd") String password,@RequestParam Integer type){

        log.info("用户名：{}，密码：{}",username,password);
        String token = userService.userLogin(username,password,type);
        return R.success("登录成功！",token);
    }

    @PostMapping("/getUser")
    @ApiOperation("由token获取用户信息")
    public R getUserInfo(HttpServletRequest request){

        String token = request.getHeader("token");

        Integer userId = TokenUtils.getUserId(token);

        if (userId == null){
            throw new CustomerException(ResultEnum.LOGIN_AUTH);
        }

        User user = userService.getById(userId);

        Resume resume = resumeService.getResumeByUserId(user.getId());
        if (resume != null) {
            user.setResumeId(resume.getId());
        }
        user.setPassword(null);
        return R.success(user);
    }

    @PostMapping("/getHR")
    @ApiOperation("由token获取HR信息")
    public R getHRInfo(HttpServletRequest request){

        String token = request.getHeader("token");
        Integer userId = TokenUtils.getUserId(token);
        if (userId == null){
            throw new CustomerException(ResultEnum.LOGIN_AUTH);
        }
        User user = userService.getById(userId);
        HrCompany hrCompany = hrCompanyService.getHrCompanyByUserId(userId);
        if(hrCompany != null){
            user.setCompanyId(hrCompany.getCompanyId());
        }
        user.setPassword(null);
        return R.success(user);
    }


    @GetMapping("/getCount")
    @ApiOperation("获取数量")
    public R getCount(){

        return R.success();
        
    }

    @GetMapping("/prev/{username}")
    @ApiOperation("获取上一次登录记录")
    public R getPrevLogin(@PathVariable("username") String  name){
        LoginLog info = loginLogService.getPrevLogin(name);
        return R.success(info);
    }
}
