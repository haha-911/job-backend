package com.milk.job.system.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.common.log.Log;
import com.milk.job.common.utils.Md5;
import com.milk.job.common.utils.TokenUtils;
import com.milk.job.model.dto.HrCompanyDto;
import com.milk.job.model.dto.UserDto;
import com.milk.job.model.pojo.HrCompany;
import com.milk.job.model.vo.UserVo;
import com.milk.job.system.service.HrCompanyService;
import com.milk.job.system.service.ResumeService;
import io.swagger.v3.oas.models.media.PasswordSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.annotation.Resources;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.milk.job.system.mapper.UserMapper;
import com.milk.job.model.pojo.User;
import com.milk.job.system.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

@Service
@Transactional
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Resource
    private HrCompanyService hrCompanyService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public int batchInsert(List<User> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public Page<UserDto> getPage(UserVo userVo) {

        Page<User> page = new Page<>(userVo.getPage(),userVo.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(!StringUtils.isEmpty(userVo.getUsername()) ,User::getUsername,userVo.getUsername())
                .eq(!StringUtils.isEmpty(userVo.getEmail()),User::getEmail,userVo.getEmail())
                .ge(!StringUtils.isEmpty(userVo.getStartTime()),User::getCreateTime,userVo.getStartTime())
                .select(User::getId,User::getUsername,User::getNickname,User::getEmail,User::getTel,User::getCreateTime,
                        User::getUpdateTime,User::getType,User::getAvatar)
                .le(!StringUtils.isEmpty(userVo.getEndTime()),User::getCreateTime,userVo.getEndTime());
        queryWrapper.eq(userVo.getType().equals("1"),User::getType,userVo.getType())
                .eq(userVo.getType().equals("3"),User::getType,userVo.getType())
                .eq(userVo.getType().equals("4"),User::getType,userVo.getType());
        Page<User> pageInfo = this.page(page, queryWrapper);

        Page<UserDto> userDtoPage = new Page<>();
        BeanUtils.copyProperties(pageInfo,userDtoPage,"records");

        List<UserDto> userDtoList = new ArrayList<>();

        for (User user:pageInfo.getRecords()){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
            if (userVo.getType().equals("3")){
                Integer userId = user.getId();
                HrCompanyDto HrCompanyDto = hrCompanyService.getHrCompanyName(userId);
                if (HrCompanyDto !=null){
                    userDto.setCompanyName(HrCompanyDto.getName());
                }
            }

            userDtoList.add(userDto);
        }

        return userDtoPage.setRecords(userDtoList);
    }

    @Override
    public void updateUser(User user) {


        if (!StringUtils.isEmpty(user.getPassword())){
            throw new CustomerException("非法修改用户信息",505);
        }

        if (user.getId() == null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }
        this.updateById(user);

    }

    @Override
    public void addUser(User user) {

        String password = Md5.md5Digest("123456");
        if (!StringUtils.isEmpty(user.getPassword())){
            password = Md5.md5Digest(user.getPassword());
        }
        user.setPassword(password);
        this.save(user);

        if (user.getType() !=null && user.getType() == 3){
            Integer companyId = user.getCompanyId();
            Integer userId = user.getId();
            hrCompanyService.addHrCompany(userId,companyId);
        }
    }

    @Override
    public void updateUserPwd(Integer id, String oldMima, String newMima) {

        if (StringUtils.isEmpty(oldMima) || StringUtils.isEmpty(newMima)){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,id).eq(User::getPassword,Md5.md5Digest(oldMima));

        User user = this.getOne(queryWrapper);

        if (user ==null){
            throw new CustomerException("原密码错误！",500);
        }

        user.setPassword(Md5.md5Digest(newMima));

        this.updateById(user);


    }

    @Override
    public void resetUserMIma(Integer id) {
        LambdaUpdateWrapper<User> queryWrapper = new LambdaUpdateWrapper<>();

        queryWrapper.eq(User::getId,id).set(User::getPassword,Md5.md5Digest("123456"));
        this.update(queryWrapper);
    }

    @Override
    public void batchRemove(List<Integer> ids) {
        baseMapper.batchRemove(ids);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.getById(id);
        if (user == null){
            throw new CustomerException(ResultEnum.DATA_ERROR);
        }
        if (user.getType() == 3){
            HrCompany hrCompany = hrCompanyService.getHrCompanyByUserId(id);
            if (hrCompany != null){
                user.setCompanyId(hrCompany.getCompanyId());
            }
        }


        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);

        return userDto;
    }


    public User getUserInfo(String username ,String password){
        if (username == null || password == null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);

        User userInfo = this.getOne(queryWrapper);

        if (userInfo == null){
            throw new CustomerException(ResultEnum.ACCOUNT_NULL);
        }

        String pwd = Md5.md5Digest(password);

        if (!pwd.equals(userInfo.getPassword())){
            throw new CustomerException(ResultEnum.ACCOUNT_ERROR);
        }

        return userInfo;
    }

    @Override
    public String login(User user) {

        User userInfo = this.getUserInfo(user.getUsername(),user.getPassword());

        if (userInfo.getType() != 1){
            throw new CustomerException(ResultEnum.USER_TYPE_ERROR);
        }
        String token = TokenUtils.createToken(userInfo.getUsername(), userInfo.getId());

//        String key  = "LoginUserInfo"+"::"+userInfo.getId();
//
//        String value = JSON.toJSONString(userInfo);
//
//        redisTemplate.opsForValue().set(key,value, Duration.ofDays(1));

        return token;
    }

    @Override
    public String userLogin(String username, String password,Integer type) {

        if ( type == null){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        User userInfo = this.getUserInfo(username,password);

        if (userInfo.getType() !=  type){
            throw new CustomerException(ResultEnum.USER_TYPE_ERROR);
        }
        String token = TokenUtils.createToken(userInfo.getUsername(), userInfo.getId());
        return token;
    }

    @Override
    public void registry(User user) {
        if (user.getUsername() == null || user.getPassword() == null ){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        if (user.getType() == null || (user.getType() !=3 && user.getType() != 4)){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(User::getUsername,user.getUsername());
        User userInfo = this.getOne(queryWrapper);
        if (userInfo !=null){
            throw new CustomerException("用户名'"+user.getUsername()+"'已存在！",500);
        }

        String password = Md5.md5Digest(user.getPassword());

        user.setPassword(password);

        user.setAvatar("http://images.beibeiaunt.icu/jobimg/72d0786bad734967803d882ce5cbbbfc.jpg");

        this.save(user);

    }
}
