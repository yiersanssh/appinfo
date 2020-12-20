package com.ssh.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.ssh.domain.DevUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    private List<String> exceptUrls;

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行了");

        DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
        if (devUser == null){
            request.getRequestDispatcher("/WEB-INF/jsp/devlogin.jsp").forward(request,response);
            return false;
        }

        return true;
    }
}
