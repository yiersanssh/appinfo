package com.ssh.listener;

import com.ssh.domain.DataDictionary;
import com.ssh.service.DicService;
import com.ssh.service.impl.DicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class SysInitListener implements ServletContextListener{

    private static Logger LOG = Logger.getLogger("SysInitListener.class");


    //private DicService dicService = new DicServiceImpl();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("上下文域对象创建了");
        LOG.info("==> 加载OMS系统配置信息 Start ==");
        try {
            //使用WebApplicationContextUtils工具类，该工具类的作用是获取到spring容器的引用，进而获取到我们需要的bean实例。
            DicService dicService = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext())
                    .getBean(DicService.class);
            ServletContext application = event.getServletContext();

            Map<String, List> map = dicService.getAll();
            //将数据字典中的值全部放入application中
            Set<String> keys = map.keySet();
            for (String key : keys){
                List<DataDictionary> list = map.get(key);
                application.setAttribute(key,list);
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOG.info(e.toString());
        }
        LOG.info("==> 加载OMS系统配置信息 End ==");
        System.out.println("数据字典处理完毕");




        /*System.out.println("上下文域对象创建了");
        ServletContext application = event.getServletContext();
        Map<String, List> map = dicService.getAll();
        //将数据字典中的值全部放入application中
        Set<String> keys = map.keySet();
        for (String key : keys){
            List<String> list = map.get(key);
            application.setAttribute(key,list);
        }
        System.out.println("数据字典处理完毕");
*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
