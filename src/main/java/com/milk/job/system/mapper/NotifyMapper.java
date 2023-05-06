package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.NotifyDto;
import com.milk.job.model.pojo.Notify;
import com.milk.job.model.vo.NotifyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

public interface NotifyMapper extends BaseMapper<Notify> {


    Page<NotifyDto> getPage(Page<Notify> page, @Param("notifyVo") NotifyVo notifyVo);

    void updateNotifyIsRead(@Param("ids") List<Integer> ids);

    List<Notify> getUserNotify(@Param("id") Integer id,@Param("isRead")Integer isRead);

    void allRead(@Param("id") Integer id);
}