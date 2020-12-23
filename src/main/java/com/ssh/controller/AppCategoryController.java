package com.ssh.controller;

import com.ssh.dao.AppCategoryMapper;
import com.ssh.domain.AppCategory;
import com.ssh.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppCategoryController {

    @Autowired
    private AppService appService;


    @RequestMapping("/categorylevellist")
    @ResponseBody
    public List<AppCategory> categorylevellist(String pid){

        List<AppCategory> categorylevellist = appService.selectAppCategoryByParentId(pid);

        return categorylevellist;
    }

}
