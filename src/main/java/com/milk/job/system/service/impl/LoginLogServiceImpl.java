package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.enums.ResultEnum;
import com.milk.job.common.exceptions.CustomerException;
import com.milk.job.model.pojo.Company;
import com.milk.job.model.pojo.User;
import com.milk.job.model.vo.LoginLogVo;
import com.milk.job.system.mapper.CompanyMapper;
import com.milk.job.system.mapper.PositionMapper;
import com.milk.job.system.mapper.UserMapper;
import org.mockito.internal.matchers.Equality;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milk.job.system.mapper.LoginLogMapper;
import com.milk.job.model.pojo.LoginLog;
import com.milk.job.system.service.LoginLogService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2023-01-12 18:19
 */

@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService{

    @Resource
    private LoginLogMapper loginLogMapper;

    @Resource
    private UserMapper userMapper;
    @Resource
    private PositionMapper positionMapper;
    @Resource
    private CompanyMapper companyMapper;

    @Override
    public Page<LoginLog> getLoginLog(LoginLogVo loginLogVo) {

        Page<LoginLog> page = new Page<>(loginLogVo.getPage(), loginLogVo.getPageSize());

        LambdaQueryWrapper<LoginLog> queryWrapper = new LambdaQueryWrapper<>();

        if (loginLogVo.getState() == null ){
            throw new CustomerException(ResultEnum.ARGUMENT_VALID_ERROR);
        }

        queryWrapper.eq(LoginLog::getStatus,loginLogVo.getState());

        return this.page(page,queryWrapper);
    }

    @Override
    public LoginLog getPrevLogin(String  name) {
        return loginLogMapper.getPrevLogin(name);

    }

    @Override
    public void delLoginlog(List<Integer> ids) {
        loginLogMapper.delLoginlog(ids);
    }

    @Override
    public HashMap<String, Object> getCount() {
        HashMap<String , Object> map = new HashMap<>();

        Integer userCount = userMapper.selectCount(4);
        map.put("user",userCount);
        Integer companyCount = companyMapper.selectCount(new LambdaQueryWrapper<>());
        map.put("company",companyCount);

        Integer positionCount = positionMapper.selectCount(new LambdaQueryWrapper<>());
        map.put("position",positionCount);

        return map;
    }
}
