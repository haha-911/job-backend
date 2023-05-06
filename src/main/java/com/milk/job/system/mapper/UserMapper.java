package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.milk.job.model.pojo.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface UserMapper extends BaseMapper<User> {
    int batchInsert(@Param("list") List<User> list);

    void batchRemove(@Param("ids") List<Integer> ids);

    Integer selectCount(@Param("type") Integer type);


}