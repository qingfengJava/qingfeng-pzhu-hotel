package com.qingfeng.utils;

import com.qingfeng.constant.MessageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 封装文件上传的代码
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public class FileUploadUtils {

    /**
     * 文件上传的方法
     * @param request
     * @param fileFieldName  要获取上传文件的名字
     * @param folderName  文件夹的名字
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public static String uploadFile(HttpServletRequest request, String fileFieldName,String folderName) throws ServletException, IOException {
        //获取上传的文件对象
        Part part = request.getPart(fileFieldName);
        //获取提交的文件的名字
        String filename = part.getSubmittedFileName();

        //容错判断，防止空文件（没有选择上传的文件）   不过一般后端不会出现问题，因为前端会做判断
        if (filename == null || filename == "") {
            return MessageConstant.FILEUPLOAD_ERROR;
        }

        //得到文件的后缀名   .jpg
        String suffixName = filename.substring(filename.lastIndexOf("."));
        //使用UUID创建一个新的文件名【目标文件名】
        String destFileName = UUID.randomUUID().toString().replace("-", "").concat(suffixName);
        //得到上传文件的目标位置
        String desPath = request.getSession().getServletContext().getRealPath(folderName);
        File file = new File(desPath,destFileName);

        //判断要上传的文件夹是否存在
        if (!file.getParentFile().exists()){
            //不存在，就创建要上传的文件夹
            file.getParentFile().mkdirs();
        }
        //上传文件到服务器上
        part.write(desPath+"/"+destFileName);

        return folderName+ destFileName;
    }
}
