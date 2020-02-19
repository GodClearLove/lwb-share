package com.baizhi.service;

import com.baizhi.conf.RandomSaltUtil;
import com.baizhi.dto.Error;
import com.baizhi.dto.FirstDTO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.User;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FirstServiceImpl implements FirstService {
    @Autowired
    BannerMapper bannerMapper;
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    Error error;
    @Autowired
    FirstDTO firstDTO;
    @Override
    public FirstDTO queryAll(Integer uid, String type, String subType) {
        List<Banner> banners = null;
        List<Album> albums = null;
        List<Article> arts = null;
        if(type.equals("all")){
            //首页
            //1.查所有轮播图
            banners = bannerMapper.queryAllByStu();
            //2.查专辑列表
            albums = albumMapper.selectAll();
            //3.查文章-上师仁波切--
            arts = articleMapper.queryAllByTea();
        }else if(type.equals("wen")){
            //闻-专辑
            albums = albumMapper.selectAll();
        }else{
            //思-文章
            if(subType.equals("ssyj")){
                //上师仁波切--特定
                arts = articleMapper.queryAllByTea();
            }else{
                //显密法要--所有
                arts = articleMapper.selectAll();
            }
        }
        firstDTO.setHeader(banners);
        firstDTO.setAlbum(albums);
        firstDTO.setArticle(arts);
        return firstDTO;
    }

    @Override
    public Article queryArticleById(Integer id) {
        Article article = articleMapper.selectOne(new Article(id, null, null, null, null, null, null));
        return article;
    }

    @Override
    public Album queryAlbumById(Integer id) {
        Album album = albumMapper.queryOneById(id);
        return album;
    }

    @Override
    public Object login(String phone, String password) {
        User user = userMapper.selectOne(new User(null, phone, null, null, null, null, null, null, null, null, null, null, null));
        if(user == null){
            //手机号错误--错误信息提示
            error.setErrno("-200");
            error.setErrorMsg("手机号已存在");
            return error;
        }else if(!user.getPassword().equals(password)){
            //密码错误--错误信息提示
            error.setErrno("-300");
            error.setErrorMsg("密码错误");
            return error;
        }else{
            //正确--返回用户对象
            return user;
        }
    }

    @Override
    public Object regist(String phone, String password) {
        //手机号查询
        User user = userMapper.selectOne(new User(null, phone, null, null, null, null, null, null, null, null, null, null, null));
        if(user != null){
            //手机号存在，注册失败--返回错误信息
            error.setErrno("-200");
            error.setErrorMsg("手机号已存在");
            return error;
        }else{
            String code = RandomSaltUtil.generetRandomSaltCode();
            user.setSalt(code);
            //MD5加密--返回
            String passNew = DigestUtils.md5Hex(password+code);
            user.setPassword(passNew);
            //添加个人信息
            userMapper.insert(user);
            //返回成功信息-密码 id 手机号
            return user;
        }

    }

    @Override
    public Object updateMsg(User user) {
        if(user != null){
            userMapper.updateByPrimaryKey(user);
            //成功返回个人信息
            return user;
        }else {
            //失败返回错误信息
            error.setErrno("-200");
            error.setErrorMsg("手机号已存在");
            return error;
        }
    }

    @Override
    public void checkCode(String phone, String code) {
        //成功返回result-success 失败返回result-fail
        User user = userMapper.selectOne(new User(null, phone, null, null, null, null, null, null, null, null, null, null, null));
        if(user == null){
            error.setErrno("-200");
            error.setErrorMsg("手机号不存在");
        }else{

        }
    }

    @Override
    public List<User> getAllMember(String uid) {
        List<User> users = userMapper.selectAll();
        return users;
    }
}
