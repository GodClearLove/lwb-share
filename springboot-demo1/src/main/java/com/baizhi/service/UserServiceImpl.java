package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Prov;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, Object> queryAllByDate() {
        Integer count1 = userMapper.queryAllByDate1();
        Integer count2 = userMapper.queryAllByDate2();
        Integer count3 = userMapper.queryAllByDate3();
        Map<String,Object> map = new HashMap<>();
        map.put("intervals",new String[]{"一周","俩周","三周"});
        map.put("count",new Integer[]{count1,count2,count3});
        return map;
    }

    @Override
    public List<Prov> queryAllByProv1() {
        List<Prov> list = userMapper.queryAllByPro1();
        return list;
    }

    @Override
    public List<Prov> queryAllByProv2() {
        List<Prov> list = userMapper.queryAllByPro2();
        return list;
    }

    @Override
    public void regist(User user) {
        userMapper.insert(user);
        //
        Map<String, Object> map = queryAllByDate();
        String s = JSONObject.toJSONString(map);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-a36c38bc99ab4be39f74d7840201943e");
        goEasy.publish("easy1",s);
        System.out.println(s+"====service");
    }


}
