package com.darkwater.service;

/**
 * Created by Mr.Darkwater on 2017/7/24.
 */

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class QiniuService {

    public static final String ACCESSKEY = "677btMCKhQoS7D67TIsmflmvABhBhnNq_g2ElRJ2";

    public static final String SECRETKEY = "tU0F70DadxdZas4yQZdWbjPvNYAUuYrEgoRNpelK";

    public static final String bucket = "weblog";

    private static final String GenerateUpToken() {
        Auth auth = Auth.create(ACCESSKEY,SECRETKEY);
        return auth.uploadToken(bucket);
    }
    public String upToCloud(MultipartFile file) {
        String filename;
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = null;
        try {
            Response response = uploadManager.put(file.getInputStream(), key, GenerateUpToken(), null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            filename = putRet.hash;
        } catch (QiniuException e) {
            System.out.println("QN问题");
            e.printStackTrace();
            filename = null;
        } catch (IOException e) {
            System.out.println("IO问题");
            e.printStackTrace();
            filename = null;
        }
        return filename;

    }
}