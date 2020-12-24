package com.ssh.controller;

import com.ssh.dao.AppVersionMapper;
import com.ssh.domain.AppInfo;
import com.ssh.domain.AppVersion;
import com.ssh.domain.DevUser;
import com.ssh.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class AppVersionController {

    @Autowired
    private AppService appService;

    @RequestMapping("/appversionadd")
    public ModelAndView appVersionAdd(String id){
        ModelAndView mv = new ModelAndView();

        //根据id查版本相关信息
        List<AppVersion> appVersionList = appService.selectAppVersionList(id);
        mv.addObject("appVersionList",appVersionList);
        mv.addObject("appId",id);
        mv.setViewName("/developer/appversionadd");
        return mv;
    }



    @RequestMapping("addversionsave")
    public String addVersionSave(HttpServletRequest request,AppVersion appVersion , @RequestParam("a_downloadLink")MultipartFile apk){

        try {
            System.out.println(appVersion.getAppid()+"===");
            //文件名称
            String fileName = apk.getOriginalFilename();
            //获取项目路径
            String realPath = request.getServletContext().getRealPath("/statics/uploadfiles");
            System.out.println("===="+realPath); //E:/IdeaProject/AppProject/appinfo/target/ssh.appinfo-1.0.0/statics/uploadfiles
            //存哪里
            String path = realPath+fileName;
            //上传
            File targetFile = new File(path);
            apk.transferTo(targetFile);

            //上传之后将版本信息更新到数据库中
            DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
            appVersion.setCreatedby(devUser.getId());
            appVersion.setCreationdate(new Date());
            appVersion.setApklocpath(path);
            appVersion.setApkfilename(fileName);


            appService.insertAppVersion(appVersion);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/dev/flatform/app/list";
    }


}
