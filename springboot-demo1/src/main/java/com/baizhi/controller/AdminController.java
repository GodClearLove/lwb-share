package com.baizhi.controller;

import com.baizhi.conf.CreateValidateCode;
import com.baizhi.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/createImg")
    @ResponseBody
    public void createImg(HttpSession session,HttpServletResponse response) throws IOException {
        CreateValidateCode cvc = new CreateValidateCode();
        String code = cvc.getCode();
        session.setAttribute("code",code);
        cvc.write(response.getOutputStream());
    }

    /*@RequestMapping("/login")
    public String login(HttpSession session,String name,String password,String vcode){
        String code =(String) session.getAttribute("code");
        if(vcode.equals(code)){
            Admin admin = adminService.login(name, password);
            if(admin!=null){
                session.setAttribute("admin",admin);
                return "redirect:/main/main.jsp";
            }else {
                return "redirect:/login.jsp";
            }
        }else{
            return "redirect:/login.jsp";
        }

    }*/
    @RequestMapping("login")
    public String login(HttpSession session,String name,String password,String vcode){
        String code =(String) session.getAttribute("code");
        if(vcode.equals(code)){
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(name,password);
            try {
                subject.login(token);
                return "redirect:/main/main.jsp";
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return "redirect:/login.jsp";
            }catch(IncorrectCredentialsException e){
                return "redirect:/login.jsp";
            }
        }else{
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/main/main.jsp";
    }
}
