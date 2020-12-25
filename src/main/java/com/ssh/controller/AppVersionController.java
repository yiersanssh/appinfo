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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    @RequestMapping("/addversionsave")
    public String addVersionSave(HttpServletRequest request,AppVersion appVersion , @RequestParam("a_downloadLink")MultipartFile apk){

        try {
            System.out.println(appVersion.getAppid()+"===");
            //文件名称
            String fileName = apk.getOriginalFilename();
            //获取项目路径
            String realPath = request.getServletContext().getRealPath("/statics/uploadfiles");
            System.out.println("===="+realPath); //E:/IdeaProject/AppProject/appinfo/target/ssh.appinfo-1.0.0/statics/uploadfiles
            //存哪里
            String path = realPath+"/"+fileName;
            //上传
            File targetFile = new File(path);
            apk.transferTo(targetFile);

            //上传之后将版本信息更新到数据库中
            DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
            appVersion.setCreatedby(devUser.getId());
            appVersion.setCreationdate(new Date());
            appVersion.setApklocpath(path);

            String downloadLink = "/app/"+path.substring(path.indexOf("statics"));
            appVersion.setDownloadlink(downloadLink);
            appVersion.setApkfilename(fileName);


            appService.insertAppVersion(appVersion);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/dev/flatform/app/list";
    }



    @RequestMapping("/appversionmodify")
    public ModelAndView appVersionModify(Long vid,String aid){
        ModelAndView mv = new ModelAndView();
        List<AppVersion> appVersionList = appService.selectAppVersionList(aid);
        mv.addObject("appVersionList",appVersionList);
        AppVersion appVersion = appService.selectAppVersion(vid);
        mv.addObject("appVersion",appVersion);

        mv.setViewName("developer/appversionmodify");
        return mv;
    }


    @RequestMapping("/delfileversion")
    @ResponseBody
    public Map<String,Object> delfile(Long id){
        Map<String,Object> map = new HashMap<>();

        //根据id查单条
        AppVersion appVersion = appService.selectAppVersion(id);

        //图片路径
        String path = appVersion.getApklocpath();
        File apk = new File(path);
        if (apk!=null){
            apk.delete();
            //将数据库中两个图片的地址全部清空
            appService.updataAppVersionApk(id);
            map.put("result","success");

        }else {
            map.put("result","failed");
        }
        return map;
    }



    @RequestMapping("/appversionmodifysave")
    public String appVersionModifySave(HttpServletRequest request,AppVersion appVersion,MultipartFile attach){
        String fileName = attach.getOriginalFilename();
        //接收文件判断文件是否有值
        if ("".equals(fileName)){
            //如果为空则不需要改变文件的地址等信息
            DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
            appVersion.setModifyby(devUser.getId());
            appVersion.setModifydate(new Date());
            appService.updataAppVersion(appVersion);


        }else {

            try {
                //如果不为空则需要将新文件上传到本地，以及将信息添加到数据库
                fileName = attach.getOriginalFilename();
                String realPath = request.getServletContext().getRealPath("/statics/uploadfiles");
                //存哪里
                String path = realPath+"/"+fileName;
                File target = new File(path);
                attach.transferTo(target);

                //更新数据库中的信息
                appVersion.setApkfilename(fileName);
                appVersion.setApklocpath(path);
                String downloadLink ="/app/"+path.substring(path.indexOf("statics"));

                appVersion.setDownloadlink(downloadLink);
                appVersion.setModifydate(new Date());
                DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
                appVersion.setModifyby(devUser.getId());
                appService.updataAppVersion(appVersion);



            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return "redirect:/dev/flatform/app/list";
    }

}
