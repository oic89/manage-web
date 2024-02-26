package com.turing.web.filter;

import com.turing.pojo.User;
import com.turing.service.UserService;
import com.turing.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class userFilter implements Filter {
    private final UserService userService=new UserServiceImpl();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        //判断是否与登录或相关
        String url=req.getRequestURL().toString();
        String[] urls={"/css/","/element-ui/","/js/","/img/","login.html","/user/","/work/","/notice/","/leave/","/admin/","/activity/","/checkCode/","/upload/","/salary/"};
        for (String u:urls){
            if (url.contains(u)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        //判断是否与管理员相关
        String[] admins={"aActivityInformation.html","aEmployeeInformation.html","aLeaveInformation.html",
        "aNoticeInformation.html","aWorkInformation.html"};
        for (String u:admins){
            if (url.contains(u)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        //判断session是否存在
        HttpSession session=req.getSession();
        Object id = session.getAttribute("userId");
        if (id==null){
            req.getRequestDispatcher("/login.html").forward(req,servletResponse);
            return;
        }
        User user = new User();
        user.setId((Integer) id);
        User user1 = userService.selectUserById(user);
        if (user1 != null && "在职".equals(user1.getIsJob())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        session.invalidate();
        req.getRequestDispatcher("/login.html").forward(req, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


}