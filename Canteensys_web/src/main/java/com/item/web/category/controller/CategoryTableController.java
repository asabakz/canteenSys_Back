package com.item.web.category.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.category.entity.CategoryTable;
import com.item.web.category.entity.ListParm;
import com.item.web.category.entity.SelectClass;
import com.item.web.category.service.CategoryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryTableController {
    @Autowired
    private CategoryTableService categoryTableService;

    // 新增
    @PostMapping
    public ResultVo add(@RequestBody CategoryTable m_Category) {
        if (categoryTableService.save(m_Category)) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody CategoryTable m_Category) {
        if (categoryTableService.updateById(m_Category)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    // 删除
    @DeleteMapping("/{categoryId}")
    public ResultVo delete(@PathVariable("categoryId") Long categoryId) {
        if (categoryTableService.removeById(categoryId)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    // 列表
    @GetMapping("/getSelectList")
    public ResultVo getSelectList() {
        List<CategoryTable> list = categoryTableService.list();
        // 组装为前端下拉选择器需要的数据格式
        List<SelectClass> selectList = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item -> {
                    SelectClass selectClass = new SelectClass();
                    selectClass.setLabel(item.getCategoryName());
                    selectClass.setValue(item.getCategoryId());
                    selectList.add(selectClass);
                });
        return ResultUtils.success("查询成功!", selectList);
    }

    // 列表
    @GetMapping("/list")
    public ResultVo list(ListParm listParm) {
        // 构造分页对象
        IPage<CategoryTable> page = new Page<>(listParm.getCurrentPage(), listParm.getPageSize());
        // 构造查询条件
        QueryWrapper<CategoryTable> query = new QueryWrapper<>();
        query.lambda().like(!(StringUtils.isEmpty(listParm.getCategoryName())), CategoryTable::getCategoryName, listParm.getCategoryName())
                .orderByAsc(CategoryTable::getOrderNum);
        IPage<CategoryTable> list = categoryTableService.page(page, query);
        return ResultUtils.success("查询成功!", list);
    }


}
