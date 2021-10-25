package com.example.interceptor;

import com.example.bean.UserBean;
import com.example.util.MyConstants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo 无需登录就可访问
        String requestURI = request.getRequestURI();
        if (requestURI.contains(".") || requestURI.startsWith("/"+ MyConstants.RESOURCE_COMMON+"/")){
            return true;
        }
        //todo 未登录，拒绝访问
        if (request.getSession().getAttribute(MyConstants.FLAG_CURRENYUSER)== null){
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("please login first");
            return false;
        }else {
            UserBean currentUser = (UserBean) request.getSession()
                    .getAttribute(MyConstants.FLAG_CURRENYUSER);
            if(requestURI.startsWith("/"+MyConstants.RESOURCE_MOBILE+"/") &&
                currentUser.havePermission(MyConstants.RESOURCE_MOBILE)){
                return true;
            }else if (requestURI.startsWith("/"+MyConstants.RESOURCE_SALARY+"/") &&
                currentUser.havePermission(MyConstants.RESOURCE_SALARY)){
                return true;
            }else {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("no auth");
                return false;
            }


        }
    }
}
