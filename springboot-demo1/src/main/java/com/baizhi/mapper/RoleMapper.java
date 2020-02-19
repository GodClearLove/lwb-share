package com.baizhi.mapper;

import com.baizhi.entity.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    List<Role> queryRole(Integer aid);
}
