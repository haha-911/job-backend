package com.milk.job.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.NotifyDto;
import com.milk.job.model.vo.NotifyVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milk.job.model.pojo.Notify;
import com.milk.job.system.mapper.NotifyMapper;
import com.milk.job.system.service.NotifyService;

/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-14 10:12
 */

@Service
public class NotifyServiceImpl extends ServiceImpl<NotifyMapper, Notify> implements NotifyService{

    @Resource
    private NotifyMapper notifyMapper;

    @Override
    public Page<NotifyDto> getPage(NotifyVo notifyVo) {

        Page<Notify> page = new Page<>(notifyVo.getPage(), notifyVo.getPageSize());
        Page<NotifyDto> pageInfo = notifyMapper.getPage(page, notifyVo);
        return pageInfo;
    }

    @Override
    public void delNotify(List<Integer> ids) {
        notifyMapper.deleteBatchIds(ids);
    }

    @Override
    public void updateNotifyIsRead(List<Integer> ids) {
        notifyMapper.updateNotifyIsRead(ids);
    }
}
