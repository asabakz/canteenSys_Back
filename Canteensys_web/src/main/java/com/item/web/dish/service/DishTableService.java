package com.item.web.dish.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.item.web.dish.entity.DishParm;
import com.item.web.dish.entity.DishTable;

public interface DishTableService extends IService<DishTable> {
  void saveDish(DishParm parm);

  void editDish(DishParm parm);

  void deleteDish(Long dishId);
}
