package com.turing.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.turing.pojo.Admin;
import com.turing.pojo.User;
import com.turing.service.AdminService;
import com.turing.service.impl.AdminServiceImpl;
import com.turing.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/checkCode/*")
public class CheckCodeServlet extends BaseServlet {
    String checkCode;

    //验证码图片
    public void checkCodeImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream os = response.getOutputStream();
        checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
    }

    //验证码字符
    public void code(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取输入验证码
        BufferedReader br = request.getReader();
        String json = br.readLine();
        JSONObject jsonObj = JSON.parseObject(json);
        String code = jsonObj.getString("checkCode");
        //验证验证码
        if (code.equalsIgnoreCase(checkCode)){
            response.getWriter().write("success");
            return;
        }
        response.getWriter().write("fail");
    }
}
