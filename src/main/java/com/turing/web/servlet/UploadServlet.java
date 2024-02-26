package com.turing.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/upload/*")
public class UploadServlet extends BaseServlet{

    //上传头像
    public void uploadFace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断上传数据是否是多段数据
        if (ServletFileUpload.isMultipartContent(request)){
            //创建FileItemFactory工厂实现类
            FileItemFactory fileItemFactory=new DiskFileItemFactory();
            //创建用于解析上传数据的ServletFileUpload类
            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);
            //解析上传的数据
            try {
                List<FileItem> list=servletFileUpload.parseRequest(request);
                //循环判断，每一个表单项是普通类型还是上传文件
                for (FileItem fileItem : list) {
                    if (!fileItem.isFormField()){
                        //上传表单项
                        HttpSession session=request.getSession();
                        String faceUrl = session.getAttribute("userId")+".jpg";
                        // 获取 Web 应用的部署目录
                        String deployPath = getServletContext().getRealPath("");
                        // 构建完整的文件路径
                        File uploadedFile = new File(deployPath + "/img/" + faceUrl);
                        fileItem.write(uploadedFile);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
