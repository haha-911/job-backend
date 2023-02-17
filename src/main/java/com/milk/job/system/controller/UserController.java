package com.milk.job.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.model.dto.UserDto;
import com.milk.job.model.pojo.User;
import com.milk.job.model.vo.UserVo;
import com.milk.job.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-11 16:21
 */
@Slf4j
@RestController
@Api(value = "用户模块" ,tags = "user")
@RequestMapping("/job/user")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID获取用户信息")
    public R getUserInfo(@PathVariable("id") Integer id){
        UserDto user = userService.getUserById(id);

        return R.success(user);
    }

    @ApiOperation(value = "分页获取用户信息")
    @PostMapping("/page")
    public R getPage(@RequestBody UserVo userVo){

        Page<UserDto> userList =  userService.getPage(userVo);

        return R.success(userList);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改用户信息")
    public R updateUser(@RequestBody User user){

        userService.updateUser(user);

        return R.success("修改成功！");
    }
    @PutMapping("/avatar")
    @ApiOperation(value = "修改头像")
    public R updateAvatar(@RequestBody User user){

        userService.updateById(user);

        return R.success("修改成功！");
    }


    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除用户信息")
    public R deleteUserById(@PathVariable("id") Integer id){

        userService.removeById(id);

        return R.success("删除成功！");
    }

    @DeleteMapping("/bathdel")
    @ApiOperation(value = "删除用户信息")
    public R deleteUserByIds(@RequestBody List<Integer> ids){

        userService.batchRemove(ids);

        return R.success("删除成功！");
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加用户信息")
    public R addUser(@RequestBody User user){

        userService.addUser(user);

        return R.success("添加成功！");
    }

    @PutMapping("/changePwd")
    @ApiOperation(value = "修改用户密码")
    public R updateUserPwd(@RequestBody Map<String,String> map){

        String  ids = map.get("id");
        String  oldPwd = map.get("oldPwd");
        String  newPwd = map.get("newPwd");
        Integer id = Integer.valueOf(ids);

        log.info("id:{},oldPwd:{},newPwd:{}",id,oldPwd,newPwd);
        userService.updateUserPwd(id,oldPwd,newPwd);

        return R.success("修改成功！");
    }
    @PutMapping("/resetPwd/{id}")
    @ApiOperation(value = "重置密码（123456）")
    public R resetUserMIma(@PathVariable("id") Integer id){

        userService.resetUserMIma(id);

        return R.success("重置成功！");
    }





}
