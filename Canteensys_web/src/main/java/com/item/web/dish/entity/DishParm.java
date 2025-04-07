package com.item.web.dish.entity;

import com.item.web.dish_specs.entity.DishSpecsTable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishParm {
    private Long dishId;
    private Long categoryId;
    private String dishName;
    private String dishImage;
    private String dishIntro;
    private String hot;
    private String dishUnit;
    private String orderNum;
    private List<DishSpecsTable> specs = new ArrayList<>();
}
