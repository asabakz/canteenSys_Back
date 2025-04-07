package com.item.web.category.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("category_table")
public class CategoryTable implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long categoryId;
    private String categoryName;
    private Integer orderNum;
}
