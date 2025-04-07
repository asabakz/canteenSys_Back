package com.item.web.ad.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.ad.entity.AdTable;
import com.item.web.ad.entity.ListParm;
import com.item.web.ad.service.AdTableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ad")
public class AdTableController {
    private AdTableService adTableService;

    // 新增
    @PostMapping
    public ResultVo add(@RequestBody AdTable ad) {
        if(adTableService.save(ad)) {
            return ResultUtils.success("创建成功！");
        }
        return ResultUtils.error("创建失败！");
    }

    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody AdTable ad) {
        if(adTableService.updateById(ad)) {
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败！");
    }

    // 删除
    @GetMapping("/{adId}")
    public ResultVo delete(@PathVariable("adId") Long adId) {
        if(adTableService.removeById(adId)) {
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败！");
    }

    // 列表
    @GetMapping("/list")
    public ResultVo list(ListParm parm) {
        // 构造分页对象
        IPage<AdTable> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());

        // 构造查询条件
        QueryWrapper<AdTable> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getTitle()), AdTable::getTitle, parm.getTitle());

        IPage<AdTable> list = adTableService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }


}
