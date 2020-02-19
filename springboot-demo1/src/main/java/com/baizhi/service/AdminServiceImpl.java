package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    /*@Autowired
    Admin admin;*/
    @Override
    public Admin login(String name,String password) {
        //admin.setName(name);
        Admin admin = adminMapper.selectOne(new Admin(null, name, null,null));
        //Admin admin1 = adminMapper.selectOne(this.admin);
        System.out.println(admin +"------");
        if (admin != null){
            if (admin.getPassword().equals(password)){
                return admin;
            }else{
                return null;
            }
        }else{
            return null;
        }

    }
}
