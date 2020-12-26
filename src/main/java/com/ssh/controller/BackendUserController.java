package com.ssh.controller;

import com.ssh.domain.BackendUser;
import com.ssh.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class BackendUserController {

    @Autowired
    private BackendUserService backendUserService;

    @RequestMapping("/manager1/login")
    public String toLogin(HttpSession session){
        //每次返回这个页面都将session清除
        session.invalidate();

        return "backendlogin";
    }


    @RequestMapping("/managerDologin")
    public ModelAndView doLogin(HttpSession session,String userCode, String userPassword){
        ModelAndView mv = new ModelAndView();
        //登录

        if (session.getAttribute("userSession")!=null){
            mv.setViewName("backend/main");
        }else {
            BackendUser backendUser = backendUserService.login(userCode,userPassword);
            if (backendUser!=null){
                mv.addObject("userSession",backendUser);
                session.setAttribute("userSession",backendUser);
                mv.setViewName("backend/main");
            }else {
                mv.addObject("error","用户名或密码错误");
                mv.setViewName("backendlogin");
            }
        }

        return mv;
    }


    @RequestMapping("/manager/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "backendlogin";
    }



}
