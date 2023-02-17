package com.milk.job.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.PositionDto;
import com.milk.job.model.pojo.Notify;
import com.milk.job.model.pojo.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import com.milk.job.model.vo.LimitVo;
import com.milk.job.model.vo.PositionVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 14:09
 */

public interface PositionService extends IService<Position> {


    PositionDto getPositionById(Integer id);

    int batchInsert(List<Position> list);

    List<PositionDto> getAll();

    Page<PositionDto> getPage(PositionVo positionVo);

    void changeState(Integer id, Integer status);

    void toExaminePosition(List<Notify> notifies);

    Page<PositionDto> selExaminePosition(PositionVo positionVo);

    Page<PositionDto> getPositionByCategoryId(LimitVo limitVo, Integer id);

    Page<PositionDto> getPositionByHRId(LimitVo limitVo, Integer id);

    void batchDelete(List<Integer> ids);

    List<Position> getPositionByCompanyId(Integer id);

    void refusePosition(List<Notify> notifys);

    Page<PositionDto> getJob(PositionVo positionVo);


}
