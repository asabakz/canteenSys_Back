package com.item.web.wechat_api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.ad.entity.AdTable;
import com.item.web.ad.service.AdTableService;
import com.item.web.category.entity.CategoryTable;
import com.item.web.category.service.CategoryTableService;
import com.item.web.dish.entity.DishTable;
import com.item.web.dish.service.DishTableService;
import com.item.web.dish_specs.entity.DishSpecsTable;
import com.item.web.dish_specs.service.DishSpecsTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wxapi/homepage")
public class WechatCategory {

    @Autowired
    private DishTableService dishTableService;

    @Autowired
    private CategoryTableService categoryTableService;
    // 各分类菜品查询
    @GetMapping("/getDishList")
    public ResultVo getDishList() {
        QueryWrapper<CategoryTable> query = new QueryWrapper<>();
        query.lambda().orderByAsc(CategoryTable::getOrderNum);
        List<CategoryTable> list = categoryTableService.list(query);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                // 查询分类下面的菜品
                QueryWrapper<DishTable> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(DishTable::getCategoryId, list.get(i).getCategoryId())
                        .orderByAsc(DishTable::getOrderNum);
                List<DishTable> dish = dishTableService.list(queryWrapper);
                list.get(i).setDish(dish);
            }
        }
        return ResultUtils.success("查询成功", list);
    }

}



