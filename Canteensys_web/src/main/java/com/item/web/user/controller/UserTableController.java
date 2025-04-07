package com.item.web.user.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.item.web.user.entity.UserPageParm;
import com.item.web.user.entity.UserTable;
import com.item.web.user.service.UserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.item.utils.ResultVo;
import com.item.utils.ResultUtils;


@RestController
@RequestMapping("/api/users")
public class UserTableController {
    @Autowired
    private UserTableService m_UserTableService;

    // 创建
    @PostMapping
    public ResultVo create(@RequestBody UserTable userTable) {
        // 检查是否有重复的 userId
        QueryWrapper<UserTable> idWrapper = new QueryWrapper<>();
        idWrapper.lambda().eq(UserTable::getUserId, userTable.getUserId());
        UserTable idDuplicate = m_UserTableService.getOne(idWrapper);
        if (idDuplicate != null) {
            return ResultUtils.error("用户ID已存在，请勿重复创建");
        }

        // 判断是否重复创建基于用户名和密码
        QueryWrapper<UserTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserTable::getUserName, userTable.getUserName())
                .eq(UserTable::getPassword, userTable.getPassword());
        UserTable cur = m_UserTableService.getOne(queryWrapper);
        if (cur != null) {
            return ResultUtils.error("用户已存在，请勿重复创建");
        }

        // 插入新用户
        if (m_UserTableService.save(userTable)) {
            return ResultUtils.success("创建成功");
        }

        return ResultUtils.error("创建失败");
    }


    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody UserTable user) {
        if (m_UserTableService.updateById(user)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    // 删除
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId) {
        if (m_UserTableService.removeById(userId)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    //简单查询
    @GetMapping("/list")
    public ResultVo list( UserPageParm parm) {
       IPage<UserTable> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
       QueryWrapper<UserTable> queryWrapper = new QueryWrapper<>();
       queryWrapper.lambda().like(!StringUtils.isEmpty(parm.getUser_name()),UserTable::getUserName,parm.getUser_name())
                            .like(!StringUtils.isEmpty(parm.getPhone_number()),UserTable::getPhoneNumber,parm.getPhone_number())
                            .orderByAsc(UserTable::getUserName);
       IPage<UserTable> userPage = m_UserTableService.page(page, queryWrapper);
       return ResultUtils.success("查询成功",userPage);
    }

}
