package com.item.web.dish.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.item.web.dish.entity.DishParm;
import com.item.web.dish.entity.DishTable;
import com.item.web.dish.mapper.DishTableMapper;
import com.item.web.dish.service.DishTableService;
import com.item.web.dish_specs.entity.DishSpecsTable;
import com.item.web.dish_specs.service.DishSpecsTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DishTableServiceImpl extends ServiceImpl<DishTableMapper, DishTable> implements DishTableService {
    @Autowired
    private DishSpecsTableService dishSpecsTableService;

    @Override
    public void saveDish(DishParm parm) {
        //1. 保存菜品
        DishTable dish = new DishTable();
        BeanUtils.copyProperties(parm, dish);
        int num = baseMapper.insert(dish);
        //2. 保存规格、价格
        if(num > 0){
            List<DishSpecsTable> specs = parm.getSpecs();
            for (int i = 0; i < specs.size(); ++i) {
                specs.get(i).setDishId(dish.getDishId());
            }
            dishSpecsTableService.saveBatch(specs);
        }

    }

    @Override
    @Transactional
    public void deleteDish(Long dishId) {
        // 删除商品
        int i = this.baseMapper.deleteById(dishId);
        // 删除规格
        if (i > 0) {
            QueryWrapper<DishSpecsTable> query = new QueryWrapper<>();
            query.lambda().eq(DishSpecsTable::getDishId, dishId);
            dishSpecsTableService.remove(query);
        }
    }


    @Override
    @Transactional
    public void editDish(DishParm parm) {
        DishTable temp = new DishTable();
        BeanUtils.copyProperties(parm, temp);
        int insert = this.baseMapper.updateById(temp);

        QueryWrapper<DishSpecsTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DishSpecsTable::getDishId, parm.getDishId());
        dishSpecsTableService.remove(queryWrapper);

        if (insert > 0) {
            List<DishSpecsTable> specs = parm.getSpecs();
            for (int i = 0; i < specs.size(); i++) {
                // 设置商品id
                specs.get(i).setDishId(temp.getDishId());
            }
            // 批量插入
            dishSpecsTableService.saveBatch(specs);
        }
    }


}

