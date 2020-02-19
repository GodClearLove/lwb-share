package com.baizhi.service;

import com.baizhi.dto.FirstDTO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.User;

import java.util.List;

public interface FirstService {
    FirstDTO queryAll(Integer uid, String type, String subType);
    //查文章详情
    Article queryArticleById(Integer id);
    //查专辑详情
    Album queryAlbumById(Integer id);
    //登录
    Object login(String phone,String password);
    //注册
    Object regist(String phone,String password);
    //修改个人信息
    Object updateMsg(User user);
    //短信验证码验证
    void checkCode(String phone,String code);
    //获取会员列表
    List<User> getAllMember(String uid);
}
