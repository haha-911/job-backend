package com.milk.job.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milk.job.common.R;
import com.milk.job.model.dto.NotifyDto;
import com.milk.job.model.pojo.Notify;
import com.milk.job.model.vo.LimitVo;
import com.milk.job.model.vo.NotifyVo;
import com.milk.job.system.service.NotifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-29 20:56
 */

@RequestMapping("/job/notify")
@RestController
@Api(value = "通知相关" ,tags = "notify")
public class NotifyController {

    @Resource
    private NotifyService notifyService;


    @PostMapping("/add")
    @ApiOperation("新增通知")
    public R addNotify(@RequestBody Notify notify){
        notifyService.save(notify);
        return R.success("操作成功！");
    }

    @PostMapping("/page")
    @ApiOperation(value = "获取所有通知")
    public R getPage(@RequestBody NotifyVo notifyVo){
        Page<NotifyDto> pageInfo = notifyService.getPage(notifyVo);
        return R.success(pageInfo);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "由id获取通知")
    public R getNotifyById(@PathVariable("id") Integer id){
        Notify notify = notifyService.getById(id);
        return R.success(notify);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改通知")
    public R updateNotify(@RequestBody Notify notify){
        notifyService.updateById(notify);
        return R.success("修改成功！");
    }

    @PutMapping("/read")
    @ApiOperation(value = "修改通知为已读")
    public R updateNotifyIsRead(@RequestBody List<Integer> ids){
        notifyService.updateNotifyIsRead(ids);
        return R.success("修改成功！");
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除通知")
    public R delNotify(@RequestBody List<Integer> ids){
        notifyService.delNotify(ids);
        return R.success("删除成功！");
    }
}
