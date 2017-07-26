package com.darkwater.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Mr.Darkwater on 2017/7/24.
 */
@Component
public class CheckUtils {
    static Logger logger = LoggerFactory.getLogger(CheckUtils.class);
    public static final String QINIUDOMIN = "http://otkzhho8u.bkt.clouddn.com/";
    final static String[] IMG_TYPES = {"png", "jpeg", "jpg", "bmp"};
    final static long MAXSIZE = 5 * 1024 * 1024;
    final static long MINSIZE = 0;
    public static final String MYDOMIN = "http://127.0.0.1:8180/";
    public static final String IMAGE_DIR = "C:\\Users\\lenovo1\\Desktop\\img";


    public static boolean checkImgType(MultipartFile file) {
        if (checkImgType(file.getName()))
        return true;
        return false;
    }

    public static boolean checkImgType(String filename) {
        if (StringUtils.lastIndexOf(filename, ".") < 0) {
//            logger.error("" + StringUtils.lastIndexOf(filename, "."));
            return false;
        }
        String suffix = StringUtils.substringAfterLast(filename, ".");
        for (String suf : IMG_TYPES) {
//            logger.error(suffix+":"+suf);
            if (0 == StringUtils.compareIgnoreCase(suffix, suf)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkImgSize(MultipartFile file) {
        if (file.getSize() > MAXSIZE || file.getSize() < MINSIZE) {
            return false;
        }
        return true;
    }

    public static String getJSONString(int code) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
        }


    public static String getJSONString(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }
        return json.toJSONString();
    }

    public static boolean isAllowUpload(String filename) {
        String suffix = suffix(filename);
        for (String cmp : IMG_TYPES) {
            if (cmp.equals(suffix)) {
                return true;
            }
        }
        return false;
    }

    public static String suffix(String filename) {

        int index = filename.lastIndexOf('.');
        if (index > 0) {
            return filename.substring(index + 1).toLowerCase();
        }
        return null;
    }
}
