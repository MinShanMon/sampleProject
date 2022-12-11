package edu.nus.iss.Clubmvc.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import edu.nus.iss.Clubmvc.controller.exception.UnauthorizedException;
import edu.nus.iss.Clubmvc.model.UserSession;

public class SecurityInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
    Object handler) throws IOException, UnauthorizedException{
        System.out.println("Intercepting" + request.getRequestURI());

        String uri = request.getRequestURI();
        if(uri.startsWith("/css/") || uri.startsWith("/image/")){
            return true;
        }
        if(uri.equalsIgnoreCase("/") || uri.equalsIgnoreCase("/home") || uri.equalsIgnoreCase("/login")
         || uri.equalsIgnoreCase("/about")){
            return true;
        }

        if(uri.startsWith("/home/")){
            return true;
        }

        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute("usession");
        if(userSession == null){
            response.sendRedirect("/login");
            return false;
        }

        List<String> userRoles = userSession.getUser().getRoleIds();

        if(uri.startsWith("/admin") && !userRoles.contains("admin")){
            throw new UnauthorizedException();
        }
        if(uri.startsWith("/staff") && !userRoles.contains("staff")){
            throw new UnauthorizedException();
        }
        return true;
    }
}
