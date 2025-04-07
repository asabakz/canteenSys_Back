package com.item.web.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.item.web.category.entity.CategoryTable;
import com.item.web.category.mapper.CategoryTableMapper;
import com.item.web.category.service.CategoryTableService;
import org.springframework.stereotype.Service;

@Service
public class CategoryTableServiceImpl extends ServiceImpl<CategoryTableMapper, CategoryTable> implements CategoryTableService {
}
