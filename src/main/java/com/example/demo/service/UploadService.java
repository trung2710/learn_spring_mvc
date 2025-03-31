package com.example.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {
    private final ServletContext servletContext; 
    public UploadService(ServletContext servletContext){
        this.servletContext=servletContext;
    }
    public String handleSaveUploadFile(MultipartFile file, String targerFolder){
        if(file.isEmpty()) return "";
        //duong dan tuong doi:relative path
        //a tra ve duon dẫn tuyệt đối đến thư mục webapp
        String a=this.servletContext.getRealPath("");
        String rootPath = this.servletContext.getRealPath("/resources/images");
        String finalName="";
        try {
            byte[] bytes;
            bytes = file.getBytes();
            //File.separator: la dau"/" 
            //phai lấy được file thôn qua đường dẫn tuyệt đối ở trong máy tính của chúng ta.  
            File dir = new File(rootPath + File.separator + targerFolder);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            finalName=System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator +finalName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close(); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return finalName;
    }
}
