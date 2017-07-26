//package com.darkwater.service;
//
//import com.google.gson.Gson;
//import com.qiniu.common.QiniuException;
//import com.qiniu.common.Zone;
//import com.qiniu.http.Response;
//import com.qiniu.storage.Configuration;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.storage.model.DefaultPutRet;
//import com.qiniu.util.Auth;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayInputStream;
//import java.io.UnsupportedEncodingException;
//
///**
// * Created by Mr.Darkwater on 2017/7/24.
// */
//@Service
//public class ImageService {
//    public String qiniuUpload() {
//        Configuration cfg = new Configuration(Zone.zone2());
//        UploadManager uploadManager = new UploadManager(cfg);
//        String accessKey = "YrCssKKKBUuxbAvASlA2SICBja7kNGW6VOmu8ck7";
//        String secretKey = "VR0bdfLAc0aOdZnY3aScVZuZXdg3-R5Tl_BQnZAf";
//        String bucket = "weblog";
//
//        String key = null;
//        try {
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
//            Auth auth = Auth.create(accessKey, secretKey);
//            String upToken = auth.uploadToken(bucket);
//            try {
//                Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
//                //解析上传成功的结果
//                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
//            } catch (QiniuException ex) {
//                Response r = ex.response;
//                System.err.println(r.toString());
//                try {
//                    System.err.println(r.bodyString());
//                } catch (QiniuException ex2) {
//                    //ignore
//                }
//            }
//        } catch (UnsupportedEncodingException ex) {
//        }
//    }
//}
