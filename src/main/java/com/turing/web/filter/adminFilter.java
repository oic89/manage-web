
package com.turing.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class adminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //判断是否与登录或员工相关
        String url = req.getRequestURL().toString();
        String[] urls = {"/css/", "/element-ui/", "/js/", "/img/", "login.html", "/user/", "/work/", "/notice/", "/leave/", "/admin/", "/activity/", "/upload/", "/salary/"};
        for (String u : urls) {
            if (url.contains(u)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        //判断是否与员工相关
        String[] admins = {"uNoticeInformation.html", "uActivityInformation.html", "uLeaveInformation.html", "uWorkInformation.html", "uSalaryInformation.html"};
        for (String u : admins) {
            if (url.contains(u)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        //判断session是否存在
        HttpSession session = req.getSession();
        Object id = session.getAttribute("adminId");
        if (id != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            req.getRequestDispatcher("/login.html").forward(req, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
