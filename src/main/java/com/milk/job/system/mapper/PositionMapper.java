package com.milk.job.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.model.dto.PositionDto;
import com.milk.job.model.pojo.Position;
import java.util.List;

import com.milk.job.model.vo.PositionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *   @Description Is Description
 *   @Author Milk
 *   @Date 2022-12-10 14:09
 */

public interface PositionMapper extends BaseMapper<Position> {
    int batchInsert(@Param("list") List<Position> list);

    List<PositionDto> getAll();

    Page<PositionDto> getPage(Page<Position> page, @Param("positionVo") PositionVo positionVo);

    Page<PositionDto> getJob(Page<Position> page,@Param("positionVo") PositionVo positionVo);

    PositionDto getPositionById(@Param("id") Integer id);

    Integer selectCountByCompanyId(@Param("id") Integer id);

    Integer selectCountByCategoryId(@Param("id") Integer id);

    List<Position> getPositionByCompanyId(@Param("id") Integer id);

    Page<Position> getRefusePosition(Page<Position> page,@Param("positionVo") PositionVo positionVo);


    void addViews(Integer id);

    List<Position> getHotPosition();

}