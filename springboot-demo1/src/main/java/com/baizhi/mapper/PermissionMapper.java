package com.baizhi.mapper;

import com.baizhi.entity.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PermissionMapper extends Mapper<Permission> {
    List<Permission> queryPerm(Integer rid);
}
