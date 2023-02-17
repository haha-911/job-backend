package com.milk.job.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.UserDto;
import com.milk.job.model.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.UserVo;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface UserService extends IService<User>{


    int batchInsert(List<User> list);

    Page<UserDto> getPage(UserVo userVo);

    void updateUser(User user);

    void addUser(User user);

    void updateUserPwd(Integer id, String oldPwd, String newPwd);

    void resetUserMIma(Integer id);

    void batchRemove(List<Integer> ids);

    UserDto getUserById(Integer id);

    String login(User user);

    String userLogin(String username, String password,Integer type);

    void registry(User user);

//    Map<String, Integer> getCount();
}
