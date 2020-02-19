package com.baizhi.mapper;

import com.baizhi.entity.Prov;
import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    Integer queryAllByDate1();
    Integer queryAllByDate2();
    Integer queryAllByDate3();
    List<Prov> queryAllByPro1();
    List<Prov> queryAllByPro2();
}
