package com.item.web.address.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.item.web.address.entity.AddressTable;
import com.item.web.address.mapper.AddressTableMapper;
import com.item.web.address.service.AddressTableService;
import org.springframework.stereotype.Service;

@Service
public class AddressTableServiceImpl extends ServiceImpl<AddressTableMapper, AddressTable> implements AddressTableService {
}
