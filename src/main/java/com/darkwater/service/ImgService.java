package com.darkwater.service;

import com.darkwater.utils.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Mr.Darkwater on 2017/7/24.
 */
@Service
public class ImgService {
    @Autowired
    QiniuService qiniuService;

    String uploadImage(MultipartFile file){
        if (!CheckUtils.isAllowUpload(file.getOriginalFilename())){
            return null;
        }
        final String filename = UUID.randomUUID().toString().replaceAll("-","")+"."+ CheckUtils.suffix(file.getOriginalFilename());
        try {
            StreamUtils.copy(file.getBytes(),new FileOutputStream(CheckUtils.IMAGE_DIR + filename));
            return CheckUtils.MYDOMIN + "image?name=" + filename;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getImage(String filename, HttpServletResponse response) throws IOException {
        response.setContentType("image/" + CheckUtils.suffix(filename));
        try{
            StreamUtils.copy(new FileInputStream(CheckUtils.IMAGE_DIR + filename),response.getOutputStream());
            return filename;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
