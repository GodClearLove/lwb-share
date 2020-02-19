package com.baizhi.controller;

import com.baizhi.entity.Prov;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/queryAllByDate")
    @ResponseBody
    public Map<String, Object> queryAllByDate(){
        Map<String, Object> map = userService.queryAllByDate();
        //String s = JSONObject.toJSONString(map);
        /*GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-a36c38bc99ab4be39f74d7840201943e");
        goEasy.publish("easy1",s);*/
        //System.out.println(s);
        return map;
    }
    @RequestMapping("queryAllByProv1")
    @ResponseBody
    public List<Prov> queryAllByProv1(){
        List<Prov> list = userService.queryAllByProv1();
        return list;
    }
    @RequestMapping("queryAllByProv2")
    @ResponseBody
    public List<Prov> queryAllByProv2(){
        List<Prov> list = userService.queryAllByProv2();
        return list;
    }
    @RequestMapping("/regist")
    public String regist(User user){
        user.setCity("武汉");
        user.setProvince("湖北");
        user.setRegDate(new Date());
        user.setSex(1);
        System.out.println(user);
        userService.regist(user);
        return "forward:/index.jsp";
    }

}
