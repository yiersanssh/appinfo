package com.ssh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssh.domain.AppInfo;
import com.ssh.domain.AppVersion;
import com.ssh.domain.BackendUser;
import com.ssh.service.AppService;
import com.ssh.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BackendUserController {

    @Autowired
    private BackendUserService backendUserService;
    @Autowired
    private AppService appService;

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


    @RequestMapping("/manager1/backend/app/list")
    public ModelAndView appList(String querySoftwareName,Long queryCategoryLevel3,Long queryCategoryLevel2,Long queryCategoryLevel1,
                                Long queryFlatformId,AppInfo appInfo,
                                @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageNum){
        ModelAndView mv = new ModelAndView();

        appInfo.setSoftwarename(querySoftwareName);
        appInfo.setFlatformid(queryFlatformId);
        appInfo.setCategorylevel1(queryCategoryLevel1);
        appInfo.setCategorylevel2(queryCategoryLevel2);
        appInfo.setCategorylevel3(queryCategoryLevel3);

        //找出所有未审核的app
        PageHelper.startPage(pageNum,5);
        List<AppInfo> appInfoList = appService.selectNoReviewAppInfo(appInfo);
        PageInfo pageInfo = new PageInfo(appInfoList);
        mv.addObject("appInfoList",appInfoList);
        mv.addObject("pages",pageInfo);


        //数据回显
        mv.addObject("querySoftwareName",querySoftwareName);
        mv.addObject("queryFlatformId",queryFlatformId);
        mv.addObject("queryCategoryLevel1",queryCategoryLevel1);
        mv.addObject("queryCategoryLevel2",queryCategoryLevel2);
        mv.addObject("queryCategoryLevel3",queryCategoryLevel3);

        mv.setViewName("backend/applist");
        return mv;
    }


}
