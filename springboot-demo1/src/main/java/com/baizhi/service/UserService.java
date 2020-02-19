package com.baizhi.service;

import com.baizhi.entity.Prov;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> queryAllByDate();
    List<Prov> queryAllByProv1();
    List<Prov> queryAllByProv2();
    void regist(User user);
}
