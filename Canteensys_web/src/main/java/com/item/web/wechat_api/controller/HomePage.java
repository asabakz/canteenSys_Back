package com.item.web.wechat_api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.item.utils.ResultUtils;
import com.item.utils.ResultVo;
import com.item.web.ad.entity.AdTable;
import com.item.web.ad.service.AdTableService;
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
@RequestMapping("/api/homepage")
public class HomePage {
    @Autowired
    private AdTableService adTableService;

    @Autowired
    private DishTableService dishTableService;

    @Autowired
    private DishSpecsTableService dishSpecsTableService;

    // 首页轮播图
    @GetMapping("/getSwiperList")
    public ResultVo getSwiperList() {
        QueryWrapper<AdTable> query = new QueryWrapper<>();
        query.lambda().eq(AdTable::getOnload, "1");
        List<AdTable> list = adTableService.list(query);
        return ResultUtils.success("查询成功", list);
    }

    // 首页热销
    @GetMapping("/getHotList")
    public ResultVo getHotList() {
        QueryWrapper<DishTable> query = new QueryWrapper<>();
        query.lambda().eq(DishTable::getHot, "1").eq(DishTable::getOnload, "1")
                .orderByAsc(DishTable::getOrderNum);
        List<DishTable> list = dishTableService.list(query);

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                // 查询价格
                QueryWrapper<DishSpecsTable> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(DishSpecsTable::getDishId, list.get(i).getDishId())
                        .orderByAsc(DishSpecsTable::getOrderNum);
                List<DishSpecsTable> specs = dishSpecsTableService.list(queryWrapper);
                list.get(i).setSpecs(specs);
            }
        }

        return ResultUtils.success("查询成功", list);
    }

}



