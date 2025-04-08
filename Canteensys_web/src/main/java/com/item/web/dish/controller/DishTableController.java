package com.item.web.dish.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.category.entity.SelectClass;
import com.item.web.dish.entity.DishParm;
import com.item.web.dish.entity.DishTable;
import com.item.web.dish.entity.ListParm;
import com.item.web.dish.service.DishTableService;
import com.item.web.dish_specs.entity.DishSpecsTable;
import com.item.web.dish_specs.service.DishSpecsTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dish")
public class DishTableController {
   @Autowired
    private DishTableService dishTableService;

   @Autowired
    private DishSpecsTableService dishSpecsTableService;

   @PostMapping
    public ResultVo add(@RequestBody DishParm parm) {
        dishTableService.saveDish(parm);
         return ResultUtils.success("创建成功");
   }

    @PutMapping
    public ResultVo edit(@RequestBody DishParm parm) {
        dishTableService.editDish(parm);
        return ResultUtils.success("编辑成功");
    }

    @GetMapping("/list")
    public ResultVo getList(ListParm parm) {
        // 构造分页对象
        IPage<DishTable> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
        // 构造查询条件
        QueryWrapper<DishTable> query = new QueryWrapper<>();
        query.lambda()
                .like(StringUtils.isNotEmpty(parm.getDishName()), DishTable::getDishName, parm.getDishName())
                .orderByDesc(DishTable::getOrderNum);
        IPage<DishTable> list = dishTableService.page(page, query);
        int list_size =list.getRecords().size();
        if(list_size > 0){
            for (int i = 0; i < list.getRecords().size(); i++) {
                QueryWrapper<DishSpecsTable> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(DishSpecsTable::getDishId, list.getRecords().get(i).getDishId())
                        .orderByAsc(DishSpecsTable::getOrderNum);
                List<DishSpecsTable> specs = dishSpecsTableService.list(queryWrapper);
                list.getRecords().get(i).setSpecs(specs);
            }
        }
        return ResultUtils.success("查询成功", list);
    }

    // 删除
    @DeleteMapping("/{dishId}")
    public ResultVo delete(@PathVariable("dishId") Long dishId) {
        dishTableService.deleteDish(dishId);
        return ResultUtils.success("删除成功!");
    }
// 列表
//列表
@GetMapping("/getSelectList")
public ResultVo getSelectList(){
    List<DishTable> list = dishTableService.list();
    //组装为前端下拉选择器需要的数据格式
    List<SelectClass> selectList = new ArrayList<>();
    Optional.ofNullable(list).orElse(new ArrayList<>())
            .stream()
            .forEach(item -> {
                SelectClass type = new SelectClass();
                type.setLabel(item.getDishName());
                type.setValue(item.getDishId());
                selectList.add(type);
            });
    return ResultUtils.success("查询成功!", selectList);
}
}
