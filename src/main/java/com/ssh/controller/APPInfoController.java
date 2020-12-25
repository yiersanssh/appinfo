package com.ssh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssh.domain.*;
import com.ssh.service.AppService;
import com.ssh.service.DicService;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class APPInfoController {

    @Autowired
    private AppService appService;

    @Autowired
    private DicService dicService;

    @RequestMapping("/dev/flatform/app/list")
    public ModelAndView applist(HttpServletRequest request,@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                String querySoftwareName,Long queryStatus,Long queryFlatformId,Long queryCategoryLevel1,Long queryCategoryLevel2,Long queryCategoryLevel3){
        AppInfo appInfo = new AppInfo();
        appInfo.setSoftwarename(querySoftwareName);
        appInfo.setStatus(queryStatus);
        appInfo.setFlatformid(queryFlatformId);
        appInfo.setCategorylevel1(queryCategoryLevel1);
        appInfo.setCategorylevel2(queryCategoryLevel2);
        appInfo.setCategorylevel3(queryCategoryLevel3);

        ModelAndView mv = new ModelAndView();

        //把applist放入请求作用域中
        PageHelper.startPage(pageIndex,5);
        List<AppInfo> appInfoList = appService.selectAppInfoByCondition(appInfo);
        mv.addObject("appInfoList",appInfoList);

        mv.addObject("querySoftwareName",querySoftwareName);
        mv.addObject("queryFlatformId",queryFlatformId);
        mv.addObject("queryStatus",queryStatus);
        mv.addObject("queryCategoryLevel1",queryCategoryLevel1);
        mv.addObject("queryCategoryLevel2",queryCategoryLevel2);
        mv.addObject("queryCategoryLevel3",queryCategoryLevel3);

        PageInfo pageInfo = new PageInfo(appInfoList);



        /*
         PageInfo{pageNum=1,
                  pageSize=5,
                  size=5,
                  startRow=1,
                  endRow=5,
                  total=11,
                  pages=3,

                  list=对象结果集

                  prePage=0,
                  nextPage=2,
                  isFirstPage=true,
                  isLastPage=false,
                  hasPreviousPage=false,
                  hasNextPage=true,
                  navigatePages=8,
                  navigateFirstPage1,
                  navigateLastPage3,
                  navigatepageNums=[1, 2, 3]}
         */



        mv.addObject("pages",pageInfo);
        mv.setViewName("developer/appinfolist");

        return mv;
    }


    @RequestMapping("/dev/flatform/app/appinfoadd")
    public String toAppInfoAddPage(){

        return "/developer/appinfoadd";
    }


    @RequestMapping("/datadictionarylist")
    @ResponseBody
    public List<Dictionary> datadictionarylist(HttpServletRequest request){
        ServletContext application = request.getServletContext();
        List<Dictionary> flatFormList = (List<Dictionary>) application.getAttribute("flatFormList");

        return flatFormList;
    }


    @RequestMapping("/categoryLevelList")
    @ResponseBody
    public List<AppCategory> categoryLevelList(HttpServletRequest request,String pid){
        System.out.println("=========================================="+pid);
        List<AppCategory> categoryLevel1List = null;
        if (pid==null||"".equals(pid)){
            ServletContext application = request.getServletContext();
            categoryLevel1List = (List<AppCategory>) application.getAttribute("categoryLevel1List");
        }else {
            categoryLevel1List = appService.selectAppCategoryByParentId(pid);
        }

        return categoryLevel1List;
    }


    @RequestMapping("apkexist")
    @ResponseBody
    public Map<String,Object> apkexist(String APKName){

        Map<String,Object> map = new HashMap<>();
        if (APKName==null||"".equals(APKName)){
            //如果为空
            map.put("APKName","empty");
        }else {
            //如果不为空则取数据库中校验是否可用
            boolean isExist = appService.selectAPKNameExist(APKName);
            if (isExist){
                map.put("APKName","exist");
            }else {
                map.put("APKName","noexist");
            }
        }
        return map;
    }




    @RequestMapping("/appinfoaddsave")
    public String appInfoAddSave(HttpServletRequest request,AppInfo appInfo,@RequestParam("a_logoPicPath") MultipartFile pic){

        try {
            System.out.println(pic);

            //获取文件名
            String fileName = pic.getOriginalFilename();

            //获取项目路径
            String realPath = request.getServletContext().getRealPath("/");//E:\IdeaProject\AppProject\appinfo\target\ssh.appinfo-1.0.0\
            System.out.println("===="+realPath);

            //存哪里
            String path = realPath+"statics/uploadfiles/"+fileName;
            System.out.println(path);//E:\IdeaProject\AppProject\appinfo\target\ssh.appinfo-1.0.0\statics/uploadfiles/1608638570(1).jpg

            //上传
            File targetFile = new File(path);
            pic.transferTo(targetFile);

            //商品文本的数据保存到数据库中
            appInfo.setLogolocpath(path);
            DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
            appInfo.setDevid(devUser.getId());
            appInfo.setCreatedby(devUser.getId());
            //获取pic路径
            String picPath = path.substring(path.indexOf("statics"));//  statics/uploadfiles/xxx.jpg
            String projectName = request.getContextPath()+"/";// /app/
            picPath = projectName+picPath;//  /app/statics/uploadfiles/xxx.jpg
            appInfo.setLogopicpath(picPath);
            //将数据保存到数据库中
            appService.addAppInfo(appInfo);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:dev/flatform/app/list";
    }



    @RequestMapping("/appinfomodify")
    public ModelAndView appInfoModify(Long id){
        ModelAndView mv = new ModelAndView();

        AppInfo appInfo = appService.selectAppInfoById(id);
        mv.addObject("appInfo",appInfo);
        mv.setViewName("developer/appinfomodify");
        return mv;
    }



    @RequestMapping("delfile")
    @ResponseBody
    public Map<String,Object> delfile(Long id,String logo){
        Map<String,Object> map = new HashMap<>();

        //根据id查单条
        AppInfo appInfo = appService.selectAppInfoById(id);

        //图片路径
        String path = appInfo.getLogolocpath();
        File pic = new File(path);
        if (pic!=null){
            pic.delete();
            //将数据库中两个图片的地址全部清空
            appService.updataAppInfoLogo(id);
            map.put("result","success");

        }else {
            map.put("result","failed");
        }

        return map;
    }





    @RequestMapping("/appinfomodifysave")
    public String appInfoModifySave(HttpServletRequest request , AppInfo appInfo,@RequestParam("attach") MultipartFile pic){
        String fileName = pic.getOriginalFilename();
        //修改Appinfo操作
        //判断是否有修改过照片
        if ("".equals(fileName)){
            //如果没修改则继续使用之前的图片路径
            DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
            appInfo.setModifyby(devUser.getId());

            appInfo.setModifydate(new Date());
            appService.updataAppInfo(appInfo);


        }else {
            //如果修改过了则使用修改后的图片路径

            try {
                //获取文件名
                fileName = pic.getOriginalFilename();

                //获取项目路径
                String realPath = request.getServletContext().getRealPath("/");//E:\IdeaProject\AppProject\appinfo\target\ssh.appinfo-1.0.0\
                System.out.println("===="+realPath);

                //存哪里
                String path = realPath+"statics/uploadfiles/"+fileName;
                System.out.println(path);//E:\IdeaProject\AppProject\appinfo\target\ssh.appinfo-1.0.0\statics/uploadfiles/1608638570(1).jpg

                //上传
                File targetFile = new File(path);
                pic.transferTo(targetFile);
                //商品文本的数据保存到数据库中
                appInfo.setLogolocpath(path);
                DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
                appInfo.setModifyby(devUser.getId());

                appInfo.setModifydate(new Date());

                //获取pic路径
                String picPath = path.substring(path.indexOf("statics"));//statics/uploadfiles/xxx.jpg
                String projectName = request.getContextPath()+"/";// /app/
                picPath = projectName+picPath;//  /app/statics/uploadfiles/xxx.jpg
                appInfo.setLogopicpath(picPath);
                //将数据保存到数据库中
                appService.updataAppInfo(appInfo);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "redirect:dev/flatform/app/list";
    }




    @RequestMapping("/appview")
    public ModelAndView appview(Long id){
        ModelAndView mv = new ModelAndView();
        //查appinfo基本信息
        AppInfo appInfo = appService.selectAppInfoMessage(id);
        mv.addObject("appInfo",appInfo);

        //查所有版本信息
        String appId = String.valueOf(id);
        List<AppVersion> appVersionList = appService.selectAppVersionList(appId);
        mv.addObject("appVersionList",appVersionList);
        mv.setViewName("developer/appinfoview");
        return mv;
    }

}
