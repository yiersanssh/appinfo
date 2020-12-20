package com.ssh.controller;

import com.ssh.domain.DevUser;
import com.ssh.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class DevUserController {

    @Autowired
    private DevUserService devUserService;

    @RequestMapping("/dev/login")
    public String login(){
        return "devlogin";
    }

    @RequestMapping("/dologin")
    public ModelAndView doLogin(String devCode, String devPassword , HttpSession session){

        ModelAndView mv = new ModelAndView();
        DevUser devUser = devUserService.dologin(devCode,devPassword);

        if (devUser == null){
            mv.addObject("error","用户名或密码错误！");
            mv.setViewName("devlogin");
        }else {
            session.setAttribute("devUserSession",devUser);
            mv.setViewName("developer/main");
        }

        return mv;
    }

    @RequestMapping("/dev/logout")
    public String logout(HttpSession session){
        //销魂session
        session.invalidate();
        return "devlogin";
    }
}
