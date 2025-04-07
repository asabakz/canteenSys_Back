package com.item.web.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.item.web.user.service.UserTableService;
import  com.item.web.user.entity.UserTable;
import  com.item.web.user.mapper.UserTableMapper;
import org.springframework.stereotype.Service;

@Service
public class UserTableServiceImpl extends ServiceImpl<UserTableMapper, UserTable> implements UserTableService {
}
