package com.ssh.controller;

import com.ssh.dao.AppVersionMapper;
import com.ssh.domain.AppVersion;
import com.ssh.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppVersionController {

    @Autowired
    private AppService appService;

    @RequestMapping("/appversionadd")
    public String appVersionAdd(String id){

        //根据id查单条





        return "/developer/appversionadd";
    }


}
